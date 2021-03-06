package com.cfpt.base.controller;

import com.cfpt.base.modal.User;
import com.cfpt.base.services.UserService;
import com.cfpt.base.utils.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dl on 2017-12-15.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@RequestBody User user) {
        if (("".equals(user.getUsername())) || user.getUsername() == null)
            return JsonResult.<String>builder().error("用户名不能为空").build();
        User _user = userService.findByUserName(user.getUsername());
        if(_user !=null)return JsonResult.<String>builder().error("用户已存在!").build();
        user.setSex("男".equals(user.getSex())?"1":user.getSex());
        user.setSex("女".equals(user.getSex())?"2":user.getSex());
        try{
            userService.insert(user);
        }catch (Exception e){
            return JsonResult.<String>builder().error(e.getMessage()).build();
        }
        return JsonResult.<String>builder().data("").build();
    }

    @ApiOperation(value = "是否存在username", notes = "是否存在username,success=true 代表存在 false 代表不存在")
    @ApiImplicitParam(name = "username", value = "username", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/isExisUserName/{username}")
    @ResponseBody
    public JsonResult isExisUserName(@PathVariable("username") String username) {
        User user = userService.findByUserName(username);
        return user == null ? JsonResult.<String>builder().error("不存在").build() : JsonResult.<String>builder().data("").build();

    }

    @ApiOperation(value = "获取用户信息", notes = "通过username获取用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/get/{username}")
    @ResponseBody
    public JsonResult findByUserName(@PathVariable("username") String username) {
        System.out.println(username);
        User user = userService.findByUserName(username);
        if (user == null)
            return JsonResult.<String>builder().error("用户不存在").build();
        user.setPassword("");
        return JsonResult.<User>builder().data(user).build();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")})
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUserName(username);
        if (user == null) return JsonResult.builder().error("用户不存在!").build();
        if (!user.getPassword().equals(password)) return JsonResult.builder().error("密码错误!").build();
        user.setPassword("");
        return JsonResult.<User>builder().build();
    }
}
