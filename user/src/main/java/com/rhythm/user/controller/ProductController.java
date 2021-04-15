package com.rhythm.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Product;
import com.rhythm.common.result.Result;
import com.rhythm.user.service.inter.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(value = "/product")
    public Result addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping(value = "/product/{id}")
    public Result DeleteProduct(@PathVariable Integer id) {
        return productService.DeleteProduct(id);
    }

    @PutMapping(value = "/product")
    public Result updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping(value = "/products")
    public Result getProducts(Page page) {
        return productService.getProducts(page);
    }
}
