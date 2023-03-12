package com.yibingo.race.security.controller;


import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import com.yibingo.race.security.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(
        value = "login",
        tags = "登陆测试"
)
@RestController
@RequestMapping("login/test")
public class AuthController {

    @Autowired
    private WeChatService weChatService;

    /**
     * code登录获取用户openid
     * @param code
     * @return
     * @throws Exception
     */
    @ApiOperation(
            value = "登录",
            notes = "登录"
    )
    @RequestMapping(
            value = "/wx",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> login(@RequestParam String code,@RequestParam String nickname,@RequestParam String head)throws Exception{
        return weChatService.wxLogin(code,nickname,head);
    }

    /**
     * 权限测试
     */

//    @GetMapping("/test")
//    public Map<String, Object> test(){
//        return GenericResponse.response(ServiceError.NORMAL,"test");
//    }
//
//    @PostMapping("/test")
//    public Map<String, Object> testPost(){
//        return GenericResponse.response(ServiceError.NORMAL,"testPOST");
//    }
//
//    @GetMapping("/test/a")
//    public Map<String, Object> testA(){
//        return GenericResponse.response(ServiceError.NORMAL,"testManage");
//    }
//
//    @GetMapping("/hello")
//    public GenericResponse hello(){
//        return GenericResponse.response(ServiceError.NORMAL,"hello security");
//    }

   /* @GetMapping("/ddd")
    @PreAuthorize("hasAuthority('ddd:list')")
    public GenericResponse ddd(){
        return GenericResponse.response(ServiceError.NORMAL,"dddList");
    }

    @PostMapping("/ddd")
    @PreAuthorize("hasAuthority('ddd:add')")
    public GenericResponse dddd(){
        return GenericResponse.response(ServiceError.NORMAL,"testPOST");
    }*/
}
