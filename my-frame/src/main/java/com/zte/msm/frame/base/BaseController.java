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
import com.zte.msm.frame.log.LoggerFactory;

/**
 * 基础控制类
 * 
 * @author JohnnyHuang黄福强
 */
@Controller
public class BaseController {

	// 日志对象
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected ResourceBundleMessageSource messageSource;
	
	protected ServiceData.RetCode SUCCESS = ServiceData.RetCode.Success;
	protected ServiceData.RetCode AUTH_FAILED = ServiceData.RetCode.AuthFailed;
	protected ServiceData.RetCode PERMISSON_DENIED = ServiceData.RetCode.PermissionDenied;
	protected ServiceData.RetCode VALIDATION_ERROR = ServiceData.RetCode.ValidationError;
	protected ServiceData.RetCode BUSINESS_ERROR = ServiceData.RetCode.BusinessError;
	protected ServiceData.RetCode SERVER_ERROR = ServiceData.RetCode.ServerError;

	protected String getMessage(HttpServletRequest request, String key) {
		RequestContext requestContext = new RequestContext(request);
		return messageSource.getMessage(key, null, requestContext.getLocale());
	}

	// 异常处理方法
	@ResponseBody
	@ExceptionHandler
	public ServiceData exception(HttpServletRequest request, Exception e) {
		logger.error("捕获到异常：", e);
		ServiceData ret = new ServiceData();
		// 根据不同的异常类型进行不同处理
		// 包括效验异常\业务异常\服务器异常等
		if (e instanceof ValidationException) {
			ret.setCode(request, VALIDATION_ERROR);
			// ret.setBo(((ValidationException) e).getResult().getAllErrors());
			ret.setBo(((ValidationException) e).getResultMap());
		} else if (e instanceof BusinessException) {
			ret.setCode(request, BUSINESS_ERROR);
			ret.setBo(e);
		} else {
			ret.setCode(request, SERVER_ERROR);
			ret.setBo(e.getMessage());
		}

		return ret;
	}

}
