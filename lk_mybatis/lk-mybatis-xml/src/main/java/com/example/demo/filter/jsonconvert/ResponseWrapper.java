package com.example.demo.filter.jsonconvert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 返回值输出代理类
 * 
 * @Title: ResponseWrapper
 * @Description:
 * @author kokJuis
 * @date 上午9:52:11
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

	private ByteArrayOutputStream buffer;

	private ServletOutputStream out;

	public ResponseWrapper(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		buffer = new ByteArrayOutputStream();
		out = new WrapperOutputStream(buffer);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return out;
	}


	@Override
	public void flushBuffer() throws IOException {
		if (out != null) {
			out.flush();
		}
	}

	public byte[] getContent() throws IOException {
		flushBuffer();
		return buffer.toByteArray();
	}


	/**
	 *  自定义WrapperOutputStream，为的是把response流写到自己指定的输入流当中.
	 *  而非默认的ServletOutputStream
	 */
	private class WrapperOutputStream extends ServletOutputStream {
		// 存放response输入流的对象
		private ByteArrayOutputStream bos;

		public WrapperOutputStream(ByteArrayOutputStream bos) {
			this.bos = bos;
		}

		@Override
		public void write(int b) throws IOException {
			bos.write(b);
		}

		@Override
		public boolean isReady() {
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {

		}
	}

}