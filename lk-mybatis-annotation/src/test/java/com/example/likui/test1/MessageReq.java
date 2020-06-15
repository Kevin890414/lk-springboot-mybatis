package com.example.likui.test1;

import java.io.Serializable;

/**
* 类说明
* @author likui 
* @since  2020年5月19日 下午6:14:02
* @version 1.0
*
*/
public class MessageReq<T> implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	private String msgId;
	
	private String msgName;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	private T requestBody;

	public T getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(T requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "MessageReq [msgId=" + msgId + ", msgName=" + msgName +",requestBody=" + requestBody  + "]";
	}
	
	
	

}
