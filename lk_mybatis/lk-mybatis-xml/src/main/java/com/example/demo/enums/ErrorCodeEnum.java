package com.example.demo.enums;
/**
* 类说明
* @author likui 
* @since  2020年11月30日 上午10:15:18
* @version 1.0
*
*/
public enum ErrorCodeEnum {
	
	SUCCESS("S","交易成功"),
	FAILED("F","交易失败"),
	DEFAULT("9","交易失败"); // 默认错误码值
	
	// 成员变量  
    private String code;  
    private String msg;
    
	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return code +"_"+msg;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    

	
	

}
