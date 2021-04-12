package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "rpst")
public interface IRpstService {

    @GetMapping(value = "/rpst/rpsts")
    Result getRpsts(@RequestParam long current, @RequestParam long size);
}
