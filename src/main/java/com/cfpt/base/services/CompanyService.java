package com.cfpt.base.services;

import com.cfpt.base.modal.Company;
import com.cfpt.base.modal.User;

/**
 * Created by Administrator on 2017-12-15.
 */
public interface CompanyService {
    public int insert(Company company);

    public Company findByUserName(String username);
}
