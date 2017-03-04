package com.zte.msm.frame.exception;

import javax.servlet.http.HttpServletRequest;

import com.zte.msm.frame.base.BaseController;
import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.util.string.StringUtil;

public class ExceptionHandler {
	public static ServiceData handler(BaseController contorller,HttpServletRequest request, Exception e){
		
		ServiceData ret = new ServiceData();
		// 根据不同的异常类型进行不同处理
		// 包括效验异常\业务异常\服务器异常等
		if (e instanceof ValidationException) {
			ret.setCode(contorller.getMessage(request,ServiceData.RetCode.ValidationError.getMsgId()),ServiceData.RetCode.ValidationError);
			if(StringUtil.isBlank(((ValidationException) e).getMessage())){
				//springmvc抛出的校验异常
				ret.setBo(((ValidationException) e).getResultMap());
			}
			else{
				//自定义校验异常
				ret.setBo(((ValidationException) e).getMessage()); 
			}
		} else if (e instanceof BusinessException) {
			ret.setCode(contorller.getMessage(request,ServiceData.RetCode.BusinessError.getMsgId()),ServiceData.RetCode.BusinessError);
			ret.setBo(((BusinessException)e).getExMsg());
		} else {
			ret.setCode(contorller.getMessage(request,ServiceData.RetCode.ServerError.getMsgId()),ServiceData.RetCode.ServerError);
			ret.setBo(e.getMessage());
		}
		return ret;
	}
}
