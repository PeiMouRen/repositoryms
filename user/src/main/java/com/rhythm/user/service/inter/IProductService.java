package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Product;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "product")
public interface IProductService {

    @PostMapping(value = "/product/product")
    Result addProduct(@RequestBody Product product);

    @DeleteMapping(value = "/product/product/{id}")
    Result DeleteProduct(@PathVariable Integer id);

    @PutMapping(value = "/product/product")
    Result updateProduct(@RequestBody Product product);

    @GetMapping(value = "/product/products")
    Result getProducts(@SpringQueryMap Page page);
}
