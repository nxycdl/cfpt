package com.cfpt.base.controller;

import com.cfpt.base.modal.Company;
import com.cfpt.base.modal.Orders;
import com.cfpt.base.modal.User;
import com.cfpt.base.services.CompanyService;
import com.cfpt.base.services.OrderService;
import com.cfpt.base.services.UserService;
import com.cfpt.base.utils.DateUtils;
import com.cfpt.base.utils.JsonResult;
import com.cfpt.base.utils.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017-12-15.
 */
@RestController
@RequestMapping(value = "/order")     // 通过这里配置使下面的映射都在/users下，可去除
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "发布订单", notes = "发布订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "companyUserName", value = "企业登录户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "caption", value = "产品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "产品详情", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "产品金额", required = true, dataType = "String", paramType = "query")})
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(@RequestParam String companyUserName, @RequestParam String caption,
                          @RequestParam String content, @RequestParam BigDecimal amount) {
        Company company = companyService.findByUserName(companyUserName);
        if (company == null) return JsonResult.builder().error("企业信息不存在!").build();
        Orders orders = new Orders();
        orders.setCompanyid(company.getId());
        orders.setCaption(caption);
        orders.setContent(content);
        orders.setAmount(amount);
        orders.setFlg("0");
        orders.setOrderid(DateUtils.Y2NO_FORMAT.format(new Date()) + StringUtils.randomNumStr(4));
        try {
            orderService.insert(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.builder().error(e.getMessage()).build();
        }
        return JsonResult.builder().data("").build();
    }

    @ApiOperation(value = "订单申请", notes = "订单申请")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "申请人", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orderid", value = "订单ID", required = true, dataType = "String", paramType = "query")})
    @PostMapping("/setOrder")
    @ResponseBody
    public JsonResult setOrder(@RequestParam String username, @RequestParam String orderid) {
        User user = userService.findByUserName(username);
        if (user==null) return JsonResult.builder().error("用户信息不存在").build();
        Orders orders = orderService.findByOrderid(orderid);
        if (orders==null) return JsonResult.builder().error("订单数据不存在").build();
        if(!"0".equals(orders.getFlg())) return JsonResult.builder().error("订单状态不正确,请重新申请订单").build();
        try{
            orderService.setOrders(user.getId(),orders.getId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.builder().error(e.getMessage()).build();
        }

        return JsonResult.builder().data("").build();
    }
}
