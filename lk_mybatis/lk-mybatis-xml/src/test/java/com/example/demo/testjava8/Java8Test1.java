package com.example.demo.testjava8;
/**
* 类说明
* @author likui 
* @since  2020年6月19日 下午3:52:18
* @version 1.0
*
*/
public class Java8Test1 {
	public static void main(String[] args) {
		// static methods
//		IConvert<String, String> convert = Something::startsWith;
//		String converted = convert.convert("123");

		
		
		 
		// object methods
//		Something something = new Something();
//		IConvert<String, String> converter = something::endWith;
//		String converted = converter.convert("Java");
		
		
		 
		// constructor methods
		IConvert<String, Something> convert = Something::new;
		Something something = convert.convert("constructors");
		
		
		
	}
}
	
	
