package com.zte.msm.spring.lov.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zte.msm.frame.base.BaseController;
import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.exception.ValidationException;
import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.spring.lov.model.LovBO;
import com.zte.msm.spring.user.business.UserService;
import com.zte.msm.spring.user.model.UserBO;


/**
 * spring mvc控制类
 */
@Controller
@RequestMapping("/lov")
public class LovController extends BaseController
{
    //日志对象
    private static final Logger logger = Logger.getLogger(LovController.class);

    //服务对象，SPRING自动装配
    @Autowired
    UserService userService;


    /**
     * 根据主键获取实体对象
     */
    @RequestMapping(value = "/insert.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("新建用户")
    public ServiceData insert(HttpServletRequest request,@Valid @RequestBody LovBO entity, BindingResult result) throws Exception
    {
    	logger.debug("--新建用户");
		//检查数据效验结果,如果有验证错误,抛出数据验证异常       
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //业务操作可以不捕获异常,由统一的异常处理方法处理
        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(entity);
        
        return ret;
    }
}