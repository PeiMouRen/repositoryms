package com.rhythm.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.entity.User;
import com.rhythm.common.result.Result;
import com.rhythm.product.entity.Product;
import com.rhythm.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(value = "/product")
    public Result addProduct(@RequestBody Product product) {
        productService.save(product);
        return Result.ok();
    }

    @DeleteMapping(value = "/product/{id}")
    public Result DeleteProduct(@PathVariable Integer id) {
        productService.removeById(id);
        return Result.ok();
    }

    @PutMapping(value = "/product")
    public Result updateProduct(@RequestBody Product product) {
       productService.updateById(product);
       return Result.ok();
    }

    @GetMapping(value = "/products")
    public Result getProducts(Page page) {
        page = productService.page(page);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
