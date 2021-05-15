package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Bzorder;
import com.rhythm.common.entity.Rpst;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order")
public interface IOrderService {

    @PostMapping(value = "/order/order")
    Result getOrders(@RequestBody Bzorder bzorder);
}
