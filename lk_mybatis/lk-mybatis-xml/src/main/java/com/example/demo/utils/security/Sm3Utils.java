package com.example.demo.utils.security;

import java.util.Objects;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
* sm3加密工具类
* @author likui 
* @since  2021年1月27日 上午9:27:28
* @version 1.0
* 
* <!-- SM3、SM4算法依赖包 -->
	<dependency>
		<groupId>org.bouncycastle</groupId>
		<artifactId>bcprov-jdk15on</artifactId>
		<version>1.66</version>
	</dependency>
	
	sm3说明:
		密文长度：32位hex串（即128bit）。
		自定义秘钥长度：没限制
	
*
*/
public class Sm3Utils {
	
	private static final String SECRET_KEY = "123123213213213yujmn1我";
	private static final String ENCODING = "UTF-8";
	
	/**   
	 * @Title: sm3EncryptNoSecretkey
	 * @Description: 方法一：不提供秘钥。sm3算法加密 
	 * @param: @param 
	 * @param: @return      
	 * @return: String   返回密文，固定长度为：32位的16进制字符串  
	 * @throws   
	 */  
	public static String sm3EncryptNoSecretkey(String srcData) {
		// 将字符串转换成byte数组
		byte[] srcDataBytes = srcData.getBytes();
		
		// 调用hash() 对原数据进行加密，获取加密后的字节数组
		byte[] hash = sm3Hash(srcDataBytes);
		
		// 将加密后的字节数据转成16进制字符串
		String resultHexString = ByteUtils.toHexString(hash);
		
		// 对16进制字符串密码 转大写
		resultHexString = resultHexString.toUpperCase();
		
		return resultHexString;
	}
	
	/**   
	 * @Title: sm3EncryptSecretkey   
	 * @Description: 方法二：自定义秘钥，sm3加密  
	 * @param: @param data
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String sm3EncryptSecretkey(String srcData) {
		
		// 将字符串转换成byte数组
		byte[] srcDataBytes = srcData.getBytes();
		// 调用hash() 对原数据进行加密，获取加密后的字节数组
		
		byte[] hash = sm3HashSecretKey(SECRET_KEY.getBytes(),srcDataBytes);
		
		// 将加密后的字节数据转成16进制字符串
		String resultHexString = ByteUtils.toHexString(hash);
		
		// 对16进制字符串密码 转大写
		resultHexString = resultHexString.toUpperCase();
		
		return resultHexString;
	}
	
	/**   
	 * @Title: hash   
	 * @Description: sm3底层加密算法   
	 * @param: @param srcData
	 * @param: @return      
	 * @return: byte[]      
	 * @throws   
	 */  
	public static byte[] sm3Hash(byte[] srcData) {
		SM3Digest digest = new SM3Digest();
		digest.update(srcData, 0, srcData.length);
		byte[] hash = new byte[digest.getDigestSize()];
		digest.doFinal(hash, 0);
		return hash;
	}
	
	/**   
	 * @Title: sm3HashSecretKey   
	 * @Description: sm3 自定义秘钥加密  
	 * @param: @param secretKey
	 * @param: @param srcData
	 * @param: @return      
	 * @return: byte[]      
	 * @throws   
	 */  
	public static byte[] sm3HashSecretKey(byte[] secretKey,byte[] srcData) {
		KeyParameter keyParameter = new KeyParameter(secretKey);
		SM3Digest digest = new SM3Digest();
		HMac mac = new HMac(digest);
		mac.init(keyParameter);
		mac.update(srcData, 0, srcData.length);
		byte[] result = new byte[mac.getMacSize()];
		mac.doFinal(result, 0);
		
		return result;
	}
	
	/**   
	 * @Title: checkSm3   
	 * @Description: 校验sm3加密数据，sm3不可逆，只能用已有的密文和 新密码密文比较
	 * @param: @param srcData
	 * @param: @param sm3HexString
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */  
	public static Boolean checkSm3(String srcData,String sm3HexString) {
		String sm3EncryptNoSecretkey = sm3EncryptNoSecretkey(srcData);
		
		return Objects.equals(sm3HexString, sm3EncryptNoSecretkey);
	}
	
	
	
	
	public static void main(String[] args) {
		
		// 不使用秘钥的sm3加密
		String sm3EncryptNoSecretkey = sm3EncryptNoSecretkey("123456");
		System.out.println(sm3EncryptNoSecretkey);
		
		// 自定义秘钥的sm3加密
		String sm3EncryptSecretkey = sm3EncryptSecretkey("123456");
		System.out.println(sm3EncryptSecretkey);
		
		// 校验sm3加密数据
		Boolean checkSm3Result = checkSm3("123456", "207CF410532F92A47DEE245CE9B11FF71F578EBD763EB3BBEA44EBD043D018FB");
		System.out.println(checkSm3Result);
		
	}

}
