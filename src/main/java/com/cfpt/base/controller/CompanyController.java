package com.cfpt.base.controller;

import com.cfpt.base.modal.Company;
import com.cfpt.base.services.CompanyService;
import com.cfpt.base.utils.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by dl on 2017-12-15.
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @ApiOperation(value = "创建用户", notes = "根据company对象创建用户")
    @ApiImplicitParam(name = "company", value = "用户详细实体company", required = true, dataType = "Company")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@RequestBody Company company) {
        if (("".equals(company.getUsername())) || company.getUsername() == null)
            return JsonResult.<String>builder().error("用户名不能为空").build();
        Company _company = companyService.findByUserName(company.getUsername());
        if(_company !=null)return JsonResult.<String>builder().error("用户已存在!").build();
        try{
            companyService.insert(company);
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
        Company company = companyService.findByUserName(username);
        return company == null ? JsonResult.<String>builder().error("不存在").build() : JsonResult.<String>builder().data("").build();

    }

    @ApiOperation(value = "获取用户信息", notes = "通过username获取用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/get/{username}")
    @ResponseBody
    public JsonResult findByUserName(@PathVariable("username") String username) {
        System.out.println(username);
        Company company = companyService.findByUserName(username);
        if (company == null)
            return JsonResult.<String>builder().error("用户不存在").build();
        company.setPassword("");
        return JsonResult.<Company>builder().data(company).build();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")})
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestParam String username, @RequestParam String password) {
        Company company = companyService.findByUserName(username);
        if (company == null) return JsonResult.builder().error("用户不存在!").build();
        if (!company.getPassword().equals(password)) return JsonResult.builder().error("密码错误!").build();
        company.setPassword("");
        return JsonResult.<Company>builder().build();
    }
}
