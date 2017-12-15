package com.cfpt.base.services;

import com.cfpt.base.mapper.CompanyMapper;
import com.cfpt.base.modal.Company;
import com.cfpt.base.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-12-15.
 */
@Service
public class CompanyServiceBean implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public int insert(Company company) {
        return companyMapper.insert(company);
    }

    @Override
    public Company findByUserName(String username) {
        return companyMapper.findByUserName(username);
    }
}
