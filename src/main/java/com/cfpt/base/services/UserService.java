package com.cfpt.base.services;
import com.cfpt.base.modal.User;
/**
 * Created by dl on 2017-12-15.
 */
public interface UserService {

    public int insert(User user);

    public User findByUserName(String username);

}
