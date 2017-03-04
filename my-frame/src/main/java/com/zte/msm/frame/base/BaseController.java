package com.zte.msm.frame.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.exception.BusinessException;
import com.zte.msm.frame.exception.ValidationException;
import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.frame.util.string.StringUtil;

/**
 * 基础控制类
 * 
 * @author JohnnyHuang黄福强
 */
@Controller
public class BaseController {

	// 日志对象
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 注入ResourceBundleMessageSource，在Controller获取
	 */
	@Autowired
	protected ResourceBundleMessageSource messageSource;
	
	protected ServiceData.RetCode SUCCESS = ServiceData.RetCode.Success;
	protected ServiceData.RetCode AUTH_FAILED = ServiceData.RetCode.AuthFailed;
	protected ServiceData.RetCode PERMISSON_DENIED = ServiceData.RetCode.PermissionDenied;
	protected ServiceData.RetCode VALIDATION_ERROR = ServiceData.RetCode.ValidationError;
	protected ServiceData.RetCode BUSINESS_ERROR = ServiceData.RetCode.BusinessError;
	protected ServiceData.RetCode SERVER_ERROR = ServiceData.RetCode.ServerError;
	
	/**
	 * 获取多语言
	 * @param request
	 * @param key
	 * @return
	 */
	public String getMessage(HttpServletRequest request, String key) {
		RequestContext requestContext = new RequestContext(request);
		return messageSource.getMessage(key, null, requestContext.getLocale());
	}

	// 异常处理方法
	@ResponseBody
	@ExceptionHandler
	@EnableLog("异常发生，记录该日志表示请求未达到方法就已经抛出。")
	protected ServiceData exception(HttpServletRequest request, Exception e) throws Exception{
		logger.error("捕获到异常：", e);
		ServiceData ret = com.zte.msm.frame.exception.ExceptionHandler.handler(this, request, e);
		logger.debug("捕获到异常-返回：{}", JacksonUtil.toJson(ret));
		return ret;
	}

}
