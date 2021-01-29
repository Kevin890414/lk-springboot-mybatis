package com.example.demo.utils.security;
/**
* 类说明
* @author likui 
* @since  2021年1月29日 下午2:47:29
* @version 1.0
*
*/
public class SecTest {
	
	public static void main(String[] args) {
		LocalKeyPair generateKeyPair = SM2Utils.generateKeyPair();
		String publicKeyString = generateKeyPair.getPublicKeyString();
		String privateKeyString = generateKeyPair.getPrivateKeyString();
		System.out.println("公钥："+publicKeyString);
		System.out.println("私钥："+privateKeyString);
		System.out.println("公钥长度："+publicKeyString.length());
		System.out.println("私钥长度："+privateKeyString.length());
		
	}
}
