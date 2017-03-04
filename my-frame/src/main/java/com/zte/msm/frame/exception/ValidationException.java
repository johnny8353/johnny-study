package com.zte.msm.frame.exception;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BindingResult result;
	
	private Map<String,String> resultMap = new Hashtable<String,String>();
	
	private String message;
	
	public BindingResult getResult() {
		return result;
	}
	
	public Map<String,String> getResultMap() {
		
		return resultMap;
	}
	
	public ValidationException(String message)
	 {
		this.message = message;
	 } 

	public ValidationException(BindingResult result)
	 {
		this.result = result;
		
		for(ObjectError objectError : this.result.getAllErrors()){
			//result.
			String code = objectError.getCodes()[0];
			resultMap.put(code.substring(code.indexOf(".")+1),objectError.getDefaultMessage());
		}

		//super("");
	 } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
