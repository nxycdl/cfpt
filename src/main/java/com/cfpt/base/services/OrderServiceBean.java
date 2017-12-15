package com.cfpt.base.services;

import com.cfpt.base.mapper.OrderMapper;
import com.cfpt.base.modal.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-12-15.
 */
@Service
public class OrderServiceBean implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int insert(Orders orders) {
        return orderMapper.insert(orders);
    }

    @Override
    public Orders findByOrderid(String orderid) {
        return orderMapper.findByOrderId(orderid);
    }

    @Override
    public int setOrders(Long userid, Long id) {
        return orderMapper.setOrders(userid,id);
    }
}
