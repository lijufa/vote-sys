package com.xinnet.controller;

import com.xinnet.entity.UserBean;
import com.xinnet.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@RequestMapping(value="/user")
@Controller
public class UserController {
    
    @Resource(name="userService")
    private UserService userService;
    
    @RequestMapping(value="/init")
    public String init(){
        return "user";
    }
    
    @RequestMapping(value="/find")
    @ResponseBody
    public List<UserBean> find(){
    	List<UserBean> list = userService.find();
        System.out.println(list);
        System.out.println(list.size());
        return list;
    }
}