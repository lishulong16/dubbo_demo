package net.dubbo.provider.controller;

import net.dubbo.common.utils.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity query(){
        return ResponseEntity.ok().build();
    }
}
