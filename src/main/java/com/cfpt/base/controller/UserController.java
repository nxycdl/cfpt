package com.cfpt.base.controller;

import com.cfpt.base.modal.User;
import com.cfpt.base.services.UserService;
import com.cfpt.base.utils.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dl on 2017-12-15.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add() {

    }

    @GetMapping(value = "/get/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path")
    public JsonResult findByUserName(@PathVariable("username") String username) {
        System.out.println(username);
        User user = userService.findByUserName(username);
        return JsonResult.<User>builder().data(userService.findByUserName(username)).build();
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "String", paramType = "path")
    @PostMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        System.out.println(id + "usernmae");
        return "success";
    }

}
