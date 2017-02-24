package com.zte.msm.spring.lov.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zte.msm.spring.user.business.UserService;


/**
 * spring mvc控制类
 */
@Controller
@RequestMapping("/user")
public class LovController
{
    //日志对象
    private static final Logger log = Logger.getLogger(LovController.class);

    //服务对象，SPRING自动装配
    @Autowired
    UserService userService;



}