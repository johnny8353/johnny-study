package com.zte.msm.frame.exception;

public class BusinessException extends Exception {

	String exCode; // 业务异常编码
	String exMsg; // 业务异常消息

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	public BusinessException(String code, String msg) {
		super(msg);
		this.exCode = code;
		this.exMsg = msg;
	}

	public BusinessException(String msg) {
		super(msg);
		this.exMsg = msg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
