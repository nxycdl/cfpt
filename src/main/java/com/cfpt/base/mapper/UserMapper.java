package com.cfpt.base.mapper;

import com.cfpt.base.modal.User;
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
    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    User findByName(@Param("username") String username);
}
