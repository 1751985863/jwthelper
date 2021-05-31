package com.csa.jwt.controller;

import com.csa.jwt.service.AdminService;
import com.csa.jwt.utils.JwtUtils;
import com.csa.jwt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> msg) {
        //①获取用户输入的账号和密码
        String username = msg.get("username");
        String password = msg.get("password");
        //②service进行操作
        String userId = adminService.LoginByUsernameAndPassword(username,password);
        //③判断
        if (userId != null) {
            //登录成功，返回token过去
            Result ok = Result.ok();
            //构建token
            Map<String, Object> map = new HashMap<>();
//            String jwtToken = JwtUtils.getJwtToken(userId, username);
//            map.put("token",jwtToken);
            map.put("token","token---");
            map.put("首页","souye");
            map.put("理财","licai");
            map.put("口碑","koubei");
            ok.data(map);
            return ok;
        }
        else {
            return Result.error();
        }
    }
    @GetMapping("souye")
    public Result getUserInfo(@RequestHeader HttpHeaders httpHeaders) {
        //首先判断header里面是否有token，以及这个token是否合法
        List<String> tokenList = httpHeaders.get("token");
        if (tokenList == null) {
            Result error = Result.error();
            error.message("没有登录,需要在请求头上添加token");
            return error;

        } else {
            String token = tokenList.get(0);
            //验证token
            if (!"token---".equals(token)) {
                Result error = Result.error();
                error.code(20003);
                error.message("token异常");
                return error;
            }
           //验证成功
        }


        Result ok = Result.ok();
        ok.message("支付宝--首页");
        Map<String, Object> map = new HashMap<>();
        map.put("花呗余额","1000");
        map.put("购买记录","买了3桶方便面");
        ok.data(map);
        return ok;

    }
    @GetMapping("licai")
    public Result licai(@RequestHeader HttpHeaders httpHeaders) {
        //首先判断header里面是否有token，以及这个token是否合法
        List<String> tokenList = httpHeaders.get("token");
        if (tokenList == null) {
            Result error = Result.error();
            error.message("没有登录,需要在请求头上添加token");
            return error;

        } else {
            String token = tokenList.get(0);
            //验证token
//            if (!"token---".equals(token)) {
//                Result error = Result.error();
//                error.code(20003);
//                error.message("token异常");
//                return error;
//            }
            if (JwtUtils.checkToken(token)) {
                Result error = Result.error();
                error.code(20003);
                error.message("token异常");
                return error;
            }
            //验证成功
        }

        Result ok = Result.ok();
        ok.message("支付宝--理财");
        Map<String, Object> map = new HashMap<>();
        map.put("总资产","10000");
        map.put("昨日收益","+0.53");
        ok.data(map);
        return ok;

    }
    @GetMapping("koubei")
    public Result getRemain(@RequestHeader HttpHeaders httpHeaders) {
        //首先判断header里面是否有token，以及这个token是否合法
        List<String> tokenList = httpHeaders.get("token");
        if (tokenList == null) {
            Result error = Result.error();
            error.message("没有登录,需要在请求头上添加token");
            return error;

        } else {
            String token = tokenList.get(0);
            //验证token
            if (!"token---".equals(token)) {
                Result error = Result.error();
                error.code(20003);
                error.message("token异常");
                return error;
            }
            //验证成功
        }

        Result ok = Result.ok();
        ok.message("支付宝--口碑");
        Map<String, Object> map = new HashMap<>();
        map.put("外卖","饿了么");
        map.put("美食","乡村基");
        ok.data(map);
        return ok;

    }




}
