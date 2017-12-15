package com.cfpt.base.mapper;

import com.cfpt.base.modal.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-12-15.
 */
@Component
@Mapper
public interface OrderMapper {
    String insertSql = "insert into `orders`(add_time, companyid,  orderid, caption, content, status, amount,status)" +
            "values(sysdate(), #{companyid},#{orderid}, #{caption}, #{content}, #{status}, #{amount},0)";

    @Insert(insertSql)
    int insert(Orders orders);

    @Select("select status,id from orders where orderid = #{orderid}")
    Orders findByOrderId(String orderid);

    @Update("update orders set apply_date= sysdate(), userid = #{userid} , status='1' where id = #{id}")
    int setOrders(@Param("userid") Long userid,@Param("id") Long id);
}
