package com.zte.msm.spring.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.spring.user.business.UserService;
import com.zte.msm.spring.user.model.UserBO;


/**
 * spring mvc控制类
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    //日志对象
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserController.class);

    //服务对象，SPRING自动装配
    @Autowired
    UserService userService;

    /**
     * 根据主键获取实体对象
     */
    @RequestMapping(value = "/get.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData get(HttpServletRequest request, @RequestBody UserBO entity) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //业务操作可以不捕获异常,由统一的异常处理方法处理
        UserBO user = userService.get(entity.getId());
        
        //ret.setCode(request, ServiceData.RetCode.Success);
        ret.setBo(user);
        
        return ret;
    }
    
    /**
     * 根据主键获取实体对象
     */
    @RequestMapping(value = "/get2.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData get2(HttpServletRequest request, @RequestBody UserBO entity) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //业务操作可以不捕获异常,由统一的异常处理方法处理
        UserBO user = new UserBO();
        user.setId(1000l);
        
        //ret.setCode(request, ServiceData.RetCode.Success);
        ret.setBo(user);
        
        return ret;
    }

}