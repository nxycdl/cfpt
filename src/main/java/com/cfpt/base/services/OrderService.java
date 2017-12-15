package com.cfpt.base.services;

import com.cfpt.base.modal.Orders;

/**
 * Created by Administrator on 2017-12-15.
 */
public interface OrderService {
    int insert(Orders orders);

    Orders findByOrderid(String orderid);

    int setOrders(Long userid, Long id);

}
