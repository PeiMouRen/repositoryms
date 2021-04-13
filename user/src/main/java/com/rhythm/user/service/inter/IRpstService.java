package com.rhythm.user.service.inter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Rpst;
import com.rhythm.user.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "rpst")
public interface IRpstService {

    @GetMapping(value = "/rpst/rpsts")
    Result getRpsts(@SpringQueryMap Page page);

    @PostMapping(value = "/rpst/rpst")
    Result addRpst(Rpst rpst);

    @PutMapping(value = "/rpst/rpst")
    Result updateRpst(Rpst rpst);

    @DeleteMapping(value = "/rpst/rpst/{id}")
    Result DeleteRpst(@PathVariable Integer id);

    @GetMapping(value = "/rpst/rpst/{id}")
    Result getRpst(@PathVariable Integer id);
}
