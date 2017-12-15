package com.cfpt.base.mapper;

import com.cfpt.base.modal.Company;
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
public interface CompanyMapper {

    String insertSql = "INSERT INTO company ( username,name, password, qyfr, lxdh, address, email, lxr )" +
            "values(#{username},#{name}, #{password}, #{qyfr}, #{lxdh}, #{address}, #{email}, #{lxr})";

    @Select("SELECT * FROM company WHERE USERNAME = #{username}")
    Company findByUserName(@Param("username") String username);

    @Insert(insertSql)
    int insert(Company company);
}
