package com.cfpt.base.mapper;

import com.cfpt.base.modal.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by dl on 2017-12-15.
 */
@Component
@Mapper
public interface UserMapper {

    String insertSql = "INSERT INTO user (username, password, name, sex, usertype, tel, address, email, lxr, khyh, yhzh )"+
            "values(#{username}, #{password}, #{name}, #{sex}, '0', #{tel}, #{address}, #{email}, #{lxr}, #{khyh}, #{yhzh})";

    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    User findByName(@Param("username") String username);

    @Insert(insertSql)
    int insert(User user);
}
