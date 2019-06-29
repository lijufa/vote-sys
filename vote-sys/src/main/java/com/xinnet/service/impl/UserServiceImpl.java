package com.xinnet.service.impl;

import com.xinnet.dao.IUserDao;
import com.xinnet.entity.UserBean;
import com.xinnet.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service(value="userService")
public class UserServiceImpl implements UserService {
    
    @Resource(name="IUserDao")
    private IUserDao userDao;
    
    @Override
    public List<UserBean> find() {
        return userDao.selectAll();
    }
    
}