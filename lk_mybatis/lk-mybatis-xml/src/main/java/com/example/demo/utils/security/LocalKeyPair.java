package com.example.demo.utils.security;

import java.io.Serializable;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;


/**
 * 密钥对16进制字符串形式存放
 * @author Administrator
 *
 */
public final class LocalKeyPair implements Serializable {
	
	
	private static final long serialVersionUID = -2185553169262795500L;
	//公钥
	private String publicKey;
	
	/**
	 * 获得公钥16进制字符串
	 * @return
	 */
	public String getPublicKeyString() {
		return publicKey;
	}
	
	/**
	 * 获得公钥字节数组
	 * @return
	 * @throws SecurityException 
	 */
	public byte[] getPublicKeyByte() throws SecurityException {
		return ByteUtils.fromHexString(publicKey);
	}
	
	/**
	 * 设置公钥字符串
	 * @param publicKey
	 */
	public void setPublicKeyString(String publicKey) {
		this.publicKey = publicKey;
	}
	/**
	 * 设置公钥数组
	 * @param publicKey
	 */
	public void setPublicKeyByte(byte[] publicKey) {
		this.publicKey = ByteUtils.toHexString(publicKey);
	}
	//私钥
	private String privateKey;
	
	/**
	 * 获得私钥字符串
	 * @return
	 */
	public String getPrivateKeyString() {
		return privateKey;
	}
	
	/**
	 * 获得私钥字节数据
	 * @return
	 * @throws SecurityException 
	 */
	public byte[] getPrivateKeyByte() throws SecurityException {
		return ByteUtils.fromHexString(privateKey);
	}
	
	/**
	 * 设置私钥字符串
	 * @param privateKey
	 */
	public void setPrivateKeyString(String privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * 设置私钥数组
	 * @param privateKey
	 */
	public void setPrivateKeyByte(byte[] privateKey) {
		this.privateKey = ByteUtils.toHexString(privateKey);
	}
	
	/**
	 * 初始化
	 * @param publicKey
	 * @param privateKey
	 */
	public LocalKeyPair(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	public LocalKeyPair(byte[] publicKey, byte[] privateKey) {
		this.publicKey = ByteUtils.toHexString(publicKey);
		this.privateKey = ByteUtils.toHexString(privateKey);
	}
	public LocalKeyPair() {
		
	}
}
