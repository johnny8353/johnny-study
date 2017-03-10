package com.zte.msm.spring.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zte.msm.frame.base.BaseController;
import com.zte.msm.frame.common.FormData;
import com.zte.msm.frame.common.PageRows;
import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.exception.ValidationException;
import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.frame.util.map.MapUtil;
import com.zte.msm.spring.user.business.UserService;
import com.zte.msm.spring.user.model.UserBO;


/**
 * spring mvc控制类
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController
{
    //日志对象
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //服务对象，SPRING自动装配
    @Autowired
    private UserService userService;
    
    public UserController() {
    	System.out.println("UserController...");
	}
    
    /**
     * 根据主键获取实体对象
     */
    @RequestMapping(value = "/get.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("根据主键获取实体对象")
    public ServiceData get(HttpServletRequest request, @RequestBody UserBO entity) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();
        if(null==entity.getId()){
        	throw new ValidationException(getMessage(request, "userBO.id.NotNull"));
//        	throw new ValidationException("Id不能为空");
        }
        //业务操作可以不捕获异常,由统一的异常处理方法处理
        UserBO user = userService.get(entity.getId());
        
        userService.get(entity.getId());
      //bo直接转换成map，设置查询条件
        Map<String, Object> map = MapUtil.toMap(user);
        userService.getList(map, "", "");
        userService.getList(map, "", "");

        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(user);

        return ret;
    }

    /**
     * 获取符合条件的实体列表,按指定属性排序,参数已post+json方式传递,
     * 这样的优势是提交的数据可以根据实体里面声明的格式自动进行格式转换,比如格式化的String传转换为Date
     */
    @RequestMapping(value = "/getList.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("获取符合条件的实体列表")
    public ServiceData getList(HttpServletRequest request, @RequestBody FormData<UserBO> from) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        Map<String, Object> map = new HashMap<String, Object>();

        //设置查询条件
        map.put("id", from.getBo().getId());
        List<UserBO> list = userService.getList(map, from.getSort(), from.getOrder());

        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(list);

        return ret;
    }


    /**
     * 删除指定记录
     */
    /**
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("删除记录")
    public ServiceData delete(HttpServletRequest request, @RequestBody UserBO entity) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        int count = userService.delete(entity.getId());

        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(new Integer(count));

        return ret;
    }

    /**
     * 新增指定记录
     */
    @RequestMapping(value = "/insert.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("新增记录")
    public ServiceData insert(HttpServletRequest request, @Valid @RequestBody UserBO entity, BindingResult result) throws Exception
    {
        //检查数据效验结果,如果有验证错误,抛出数据验证异常
        if (result != null && result.hasErrors())
        {
            throw new ValidationException(result);
        }

        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //业务操作可以不捕获异常,由统一的异常处理方法处理
        int count = userService.insert(entity);

        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(new Integer(count));

        return ret;
    }

    /**
     * 修改指定记录
     */
    @RequestMapping(value = "/update.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("修改记录")
    public ServiceData update(HttpServletRequest request, @Valid @RequestBody UserBO entity, BindingResult result) throws Exception
    {
        //检查数据效验结果,如果有验证错误,抛出数据验证异常
        if (result != null && result.hasErrors())
        {
            throw new ValidationException(result);
        }

        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //业务操作可以不捕获异常,由统一的异常处理方法处理
        int count = userService.update(entity);

        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(new Integer(count));

        return ret;
    }

    /**
     * 获取符合条件的分页记录，包括当页数据/记录总数,
     * 先按指定属性排序,再分页
     */
    @RequestMapping(value = "/getPage.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @EnableLog("获取符合条件的分页记录，包括当页数据/记录总数")
    public ServiceData getPage(HttpServletRequest request, @RequestBody FormData<UserBO> form) throws Exception
    {
    	logger.debug("{}",JacksonUtil.toJson(form));
    	
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();

        //bo直接转换成map，设置查询条件
        Map<String, Object> map = MapUtil.toMap(form.getBo());
        

        PageRows<UserBO> page = userService.getPage(map, form.getSort(), form.getOrder(), form.getPage(), form.getRows());
        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(page);
        Map<Object,Object> other = new HashMap<Object, Object>();
    	other.put("test", page);
    	ret.setOther(other);
        return ret;
    }
    
    /**
     * 根据主键获取实体对象
     */
    @RequestMapping(value = "/get2.serv", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @EnableLog("查询用户")
    @ResponseBody
    public ServiceData get2(HttpServletRequest request, @RequestBody UserBO entity) throws Exception
    {
        //返回统一的服务端数据
        ServiceData ret = new ServiceData();
        logger.debug("--inputs:{}",entity);
        //业务操作可以不捕获异常,由统一的异常处理方法处理
        UserBO user = new UserBO();
        user.setId(1000l);
        
        ret.setCode(getMessage(request,SUCCESS.getMsgId()),SUCCESS);
        ret.setBo(user);
//        if(1==1){
//        	throw new Exception("333");
//        }
        
        return ret;
    }
    
}