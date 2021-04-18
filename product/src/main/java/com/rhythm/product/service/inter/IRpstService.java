package com.rhythm.product.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "rpst")
public interface IRpstService {

    @GetMapping(value = "/rpst/rpst/{rpstId}")
    Result getRpst(@PathVariable Integer rpstId);
}
