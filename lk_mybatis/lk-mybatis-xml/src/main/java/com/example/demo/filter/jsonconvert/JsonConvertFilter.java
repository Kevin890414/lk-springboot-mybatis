package com.example.demo.filter.jsonconvert;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 使用过滤器，将请求报文、响应报文 结构进行修改（根据）
 * 
 * @author likui
 * @since 2021年2月4日 下午1:50:37
 * @version 1.0
 *
 */
public class JsonConvertFilter implements Filter {
	public static final String MODULE = JsonConvertFilter.class.getName();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(request+"进入过滤器");
		
		// 创建请求requestWrapper
		RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
		
		// 更改报文类型
		String requestURL = requestWrapper.getRequestURL().toString();
		System.out.println("getLocalAddr"+requestURL);
		String requestURI = requestWrapper.getRequestURI();
		System.out.println("getLocalAddr"+requestURI);
		
		// 报文格式，默认平铺报文，不用转换
		String jsonFormatType = "COMMON"; 
		if (requestURI.startsWith("/Sec")) {
			jsonFormatType = "DEV"; // 研发报文
		} else if (requestURI.startsWith("/Iap")) {
			jsonFormatType = "XJ"; // 新疆报文
		} 
		
		// 转换请求报文格式
		Map<String,Object> inputJsonMap = JSONObject.parseObject(requestWrapper.getBody(), Map.class);
		
		if (!jsonFormatType.equals("COMMON")) {
			
			if ("DEV".equals(jsonFormatType)) {
				// 研发报文
				updateInputJsonMapToDev(inputJsonMap);
				requestWrapper.setBody(JSONObject.toJSONString(inputJsonMap));
			} else {
				// 新疆报文
				
			}
			
			// 转换成代理类
			ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse)response);
			
			chain.doFilter(requestWrapper, response);
			
			byte[] content = responseWrapper.getContent();
			
			if (content.length > 0)  {
	 
	            String responseJson = new String(content, "UTF-8");
	 
	            Map<String,Object> outJsonMap = JSONObject.parseObject(responseJson, Map.class);
	            if ("DEV".equals(jsonFormatType)) {
					// 研发报文
		        	updateOutputJsonMapToDev(outJsonMap);
		        	
				} else {
					// 新疆报文
					
				}
	            
	            //把返回值输出到客户端
	            ServletOutputStream out = response.getOutputStream();
	            out.write(JSONObject.toJSONString(outJsonMap).getBytes());
	            out.flush();
	            out.close();
	        	
	        		
	    		
			}
		} else {
			chain.doFilter(requestWrapper, response);
		}
        
		
		
		
	}

	@Override
	public void destroy() {

	}

	private void updateInputJsonMapToDev(Map<String, Object> inputJsonMap) {
		Map<String, Object> headerMap = new HashMap<>();
		// 修改报文格式
		headerMap.put("LocalLang", inputJsonMap.remove("LocalLang"));
		headerMap.put("TranTeller", inputJsonMap.remove("TranTeller"));
		headerMap.put("ExtendContent", inputJsonMap.remove("ExtendContent"));
		headerMap.put("TranDate", inputJsonMap.remove("TranDate"));
		headerMap.put("TranCode", inputJsonMap.remove("TranCode"));
		headerMap.put("AuthFlag", inputJsonMap.remove("AuthFlag"));
		headerMap.put("Channel", inputJsonMap.remove("Channel"));
		headerMap.put("KeyId", inputJsonMap.remove("KeyId"));
		headerMap.put("TranSeq", inputJsonMap.remove("TranSeq"));
		headerMap.put("BranchId", inputJsonMap.remove("BranchId"));
		headerMap.put("GlobalSeq", inputJsonMap.remove("GlobalSeq"));
		headerMap.put("Mac", inputJsonMap.remove("Mac"));
		headerMap.put("ClientIp", inputJsonMap.remove("ClientIp"));
		headerMap.put("ServiceCode", inputJsonMap.remove("ServiceCode"));
		headerMap.put("TerminalCode", inputJsonMap.remove("TerminalCode"));
		headerMap.put("ServiceName", inputJsonMap.remove("ServiceName"));
		headerMap.put("AuthrPwd", inputJsonMap.remove("AuthrPwd"));
		headerMap.put("AuthrTeller", inputJsonMap.remove("AuthrTeller"));
		headerMap.put("ConsumerId", inputJsonMap.remove("ConsumerId"));
		headerMap.put("SourceSysId", inputJsonMap.remove("SourceSysId"));
		headerMap.put("TranTime", inputJsonMap.remove("TranTime"));
		headerMap.put("LegalRepCode", inputJsonMap.remove("LegalRepCode"));
		
		inputJsonMap.put("Header", headerMap);
	}
	private void updateOutputJsonMapToDev(Map<String, Object> outJsonMap) {
		Map<String, Object> headerMap = (Map<String, Object>)outJsonMap.get("Header");
		// 修改报文格式
		if (headerMap != null) {
			outJsonMap.put("LocalLang", headerMap.get("LocalLang"));
			outJsonMap.put("TranTeller", headerMap.get("TranTeller"));
			outJsonMap.put("ExtendContent", headerMap.get("ExtendContent"));
			outJsonMap.put("TranDate", headerMap.get("TranDate"));
			outJsonMap.put("TranCode", headerMap.get("TranCode"));
			outJsonMap.put("AuthFlag", headerMap.get("AuthFlag"));
			outJsonMap.put("Channel", headerMap.get("Channel"));
			outJsonMap.put("KeyId", headerMap.get("KeyId"));
			outJsonMap.put("TranSeq", headerMap.get("TranSeq"));
			outJsonMap.put("BranchId", headerMap.get("BranchId"));
			outJsonMap.put("GlobalSeq", headerMap.get("GlobalSeq"));
			outJsonMap.put("Mac", headerMap.get("Mac"));
			outJsonMap.put("ClientIp", headerMap.get("ClientIp"));
			outJsonMap.put("ServiceCode", headerMap.get("ServiceCode"));
			outJsonMap.put("TerminalCode", headerMap.get("TerminalCode"));
			outJsonMap.put("ServiceName", headerMap.get("ServiceName"));
			outJsonMap.put("AuthrPwd", headerMap.get("AuthrPwd"));
			outJsonMap.put("AuthrTeller", headerMap.get("AuthrTeller"));
			outJsonMap.put("ConsumerId", headerMap.get("ConsumerId"));
			outJsonMap.put("SourceSysId", headerMap.get("SourceSysId"));
			outJsonMap.put("TranTime", headerMap.get("TranTime"));
			outJsonMap.put("LegalRepCode", headerMap.get("LegalRepCode"));
			outJsonMap.put("RetCode", headerMap.get("RetCode"));
			outJsonMap.put("RetStatus", headerMap.get("RetStatus"));
			outJsonMap.put("RetMsg", headerMap.get("RetMsg"));
		}
		
		outJsonMap.remove("Header");
		
	}

	private Map<String, Object> getInputJsonMap2(ServletRequest request) throws IOException {
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;

		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);
		Map<String, Object> map = JSONObject.parseObject(responseStrBuilder.toString(), Map.class);
		return map;
	}

	private Map<String, Object> getInputJsonMap(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buff[] = new byte[1024];
		int read;
		while ((read = inputStream.read(buff)) > 0) {
			baos.write(buff, 0, read);
		}
		byte[] byteArray = baos.toByteArray();

		String json = new String(byteArray);

		Map<String, Object> parseObject = JSONObject.parseObject(json, Map.class);

		return parseObject;
	}

}
