package com.example.demo.myexception;
/**
* 自定义异常类
* @author likui 
* @since  2020年11月30日 上午9:51:43
* @version 1.0
*
*/
public class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**   
	 * @Fields errorCode : 错误码
	 */ 
	private String errorCode;
	
	/**   
	 * @Fields errorMsg : 错误信息 
	 */ 
	private String errorMsg;
	
	/**   
	 * @Fields INTERVAL_MARK : 错误码、错误信息分隔符
	 */ 
	private static final String INTERVAL_MARK = "@#@";
	
	
	//构造函数
    public MyException(String errorCode,String errorMsg){
    	super(errorCode +INTERVAL_MARK +errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
    

}
