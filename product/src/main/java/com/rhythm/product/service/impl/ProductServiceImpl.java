package com.rhythm.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.Overdue;
import com.rhythm.common.entity.Bzorder;
import com.rhythm.common.result.Result;
import com.rhythm.product.Enum.ProductOperate;
import com.rhythm.product.entity.Product;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.product.service.inter.IOrderService;
import com.rhythm.product.service.inter.IRpstService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IRpstService rpstService;
    @Autowired
    private IOrderService orderService;

    @Override
    public Result updateInventory(String userName, Integer rpstId, Integer productId, Integer productNum, Integer operate, String des, String optName) {
        Result result = new Result();
        log.info(rpstService.getRpst(rpstId).toString());
        Product product = productMapper.selectById(productId);
        int productSize = product.getSize();
        Map<String, Object> rpst = (Map<String, Object>)(rpstService.getRpst(rpstId).getData());
        int size = (int)rpst.get("size");
        int num = productMapper.getNum(rpstId);
        Map<String, Integer> map = productMapper.selectInventory1(rpstId, productId);
        if (operate == ProductOperate.IN.getOperate()) {
            optName = product.getSupplyName();
            // 入库先判断库存中有没有，没有的话先插入
            if (map == null) {
                // 没有库存，判断入库的数量是否超标
                if (productNum * productSize > size - num) {
                    result.setCode(201);
                    result.setMessage("入库失败，仓库容量不足！");
                    return result;
                } else {
                    productMapper.insertInventory(rpstId, productId, productNum);
                    generateOrder(userName, rpstId, productId, productNum, operate, des, optName);
                    result.setCode(200);
                    return result;
                }
            } else {
                if (productNum * productSize > size - num) {
                    result.setCode(201);
                    result.setMessage("入库失败，仓库容量不足！");
                    return result;
                } else {
                    productMapper.updateInventory(rpstId, productId, productNum, operate);
                    generateOrder(userName, rpstId, productId, productNum, operate, des, optName);
                    result.setCode(200);
                    return result;
                }
            }
        } else {
            int oldNum = map.get("productNum");
            if (productNum > oldNum) {
                result.setCode(201);
                result.setMessage("出库失败，库存不足！");
                return result;
            } else {
                productMapper.updateInventory(rpstId, productId, productNum, operate);
                generateOrder(userName, rpstId, productId, productNum, operate, des, optName);
                result.setCode(200);
                return result;
            }
        }
    }

    private Result generateOrder(String userName, Integer rpstId, Integer productId, Integer productNum, Integer operate, String des, String optName) {
        Bzorder order = new Bzorder();
        order.setType(operate);
        order.setUserName(userName);
        Map<String, Object> map = null;
        map = (Map<String, Object>)(rpstService.getRpst(rpstId).getData());
        order.setRpstName((String)map.get("name"));
        order.setProductName(productMapper.selectById(productId).getName());
        order.setProductNum(productNum);
        order.setTime(LocalDateTime.now());
        order.setDes(des);
        order.setOptName(optName);
        return orderService.addOrder(order);
    }

    @Override
    public Page getInventory(Page page, Integer rpstId) {
        page = productMapper.selectInventory(page, rpstId);
        List<Product> records = new ArrayList<>();
        int temp1 = 1;
        int temp2 = 1;
        for (Object obj : page.getRecords()) {
            Map<String, Integer> map = (Map<String, Integer>)obj;
            Product product = productMapper.selectById(map.get("productId"));
            product.setProductNum(map.get("productNum"));
            int size = map.get("productNum") * product.getSize();
            temp2 = temp1 + size - 1;
            String location = temp1 + "-" + temp2;
            temp1 = temp2 + 1;
            product.setLocation(location);

            records.add(product);
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public List<String> getProductTypes() {
        return productMapper.getProductTypes();
    }

    @Override
    public Integer getUsed(Integer productId) {
        return productMapper.getUsed(productId);
    }

    @Override
    public Integer isOverdue(Product product) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.from(product.getProductionDate().toInstant(ZoneOffset.of("+8"))));
            calendar.add(Calendar.MONTH, product.getDuration());
            long time = calendar.getTimeInMillis() - LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            if (time <= 0) {
                return Overdue.EXPIRED.getOverdue(); // 已过期
            }

            long day = time / (24 * 60 * 60 * 1000);
            if (day <= Overdue.EXPIREDDAY.getOverdue()) {
                return Overdue.BEEXPIRED.getOverdue(); // 即将过期
            }
            return Overdue.UNEXPIRED.getOverdue(); // 未过期
        } catch (Exception e) {
            return Overdue.UNEXPIRED.getOverdue(); // 未过期
        }

    }

    @Override
    public Page getByFilter(Page page, Product product) {
        page = productMapper.getByFilter(page, product);

        int temp1 = 1;
        int temp2 = 1;
        List<Product> temps = page.getRecords();
        for (Product temp : temps) {
            if (getUsed(temp.getId()) == 0) {
                temp.setUsed(false);
            } else {
                temp.setUsed(true);
            }

            temp.setOverdue(isOverdue(temp));

            if (temp.getRpstId() != null) {
                int size = temp.getProductNum() * temp.getSize();
                temp2 = temp1 + size - 1;
                String location = temp1 + "-" + temp2;
                temp1 = temp2 + 1;
                temp.setLocation(location);
            }

        }
        page.setRecords(temps);
        return page;
    }

    public static void main(String[] args) {
        LocalDateTime llt = LocalDateTime.of(LocalDate.of(2021,4,1), LocalTime.of(20,31,20));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(llt.toInstant(ZoneOffset.of("+8"))));
        calendar.add(Calendar.MONTH, 1);
        System.out.println(calendar.getTime());
        long a = calendar.getTimeInMillis();

        LocalDateTime now = LocalDateTime.now();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(Date.from(now.toInstant(ZoneOffset.of("+8"))));
        System.out.println(calendar1.getTime());
        long b = calendar1.getTimeInMillis();
        System.out.println(b + "-" + a + "=" + (b - a));
    }
}
