package com.example.demo.utils.security;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
*  sm2算法工具类
* @author likui 
* @since  2021年1月28日 下午2:27:58
* @version 1.0
*
*/
public class SM2Utils {

	/**
	 * 生成密钥对
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static LocalKeyPair generateKeyPair(){
		// 获取一条SM2曲线参数
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");

		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());

		// 1.创建密钥生成器
		ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();

		// 2.初始化生成器,带上随机数
		keyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, new SecureRandom()));

		// 3.生成密钥对
		AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();

		return new LocalKeyPair(((ECPublicKeyParameters) asymmetricCipherKeyPair.getPublic()).getQ().getEncoded(false),
				((ECPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate()).getD().toByteArray());
	}

	/**
	 * 公钥加密
	 * 
	 * @param pk
	 * @param data
	 * @return
	 * @throws SecurityException
	 * @throws com.hsm.security.SecurityException
	 */
	public static final byte[] encrypt(byte[] publicKey, byte[] data) throws SecurityException {
		// 获取一条SM2曲线参数
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());
		// 提取公钥点
		ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(publicKey);
		// 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
		ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

		SM2Engine sm2Engine = new SM2Engine();
		sm2Engine.init(true, new ParametersWithRandom(publicKeyParameters, new SecureRandom()));

		byte[] arrayOfBytes = null;
		try {
			byte[] in = data;
			arrayOfBytes = sm2Engine.processBlock(in, 0, in.length);
		} catch (Exception e) {
			arrayOfBytes = new byte[0];
		}
		return arrayOfBytes;
	}

	/**
	 * 私钥解密
	 * 
	 * @param dk
	 * @param data
	 * @return
	 * @throws com.hsm.security.SecurityException
	 */
	public static final byte[] decrypt(byte[] privateKey, byte[] data) throws SecurityException {

		// 获取一条SM2曲线参数
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());

		ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(new BigInteger(privateKey),
				domainParameters);

		SM2Engine sm2Engine = new SM2Engine();
		sm2Engine.init(false, privateKeyParameters);

		byte[] arrayOfBytes = null;
		try {
			arrayOfBytes = sm2Engine.processBlock(data, 0, data.length);
		} catch (Exception e) {
			arrayOfBytes = new byte[0];
		}
		return arrayOfBytes;

	}

	/**
	 * 私钥签名
	 * 
	 * @param privateKey
	 * @param plainText
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws CryptoException
	 * @throws com.hsm.security.SecurityException
	 */
	public static final byte[] signature(byte[] privateKey, byte[] plainText, byte[] ida)
			throws NoSuchAlgorithmException, CryptoException, SecurityException {
		// 获取一条SM2曲线参数
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());

		BigInteger privateKeyD = new BigInteger(privateKey);
		ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);

		// 创建签名实例
		SM2Signer sm2Signer = new SM2Signer();

		// 初始化签名实例,带上ID,国密的要求,ID默认值:1234567812345678
		try {
			sm2Signer.init(true, new ParametersWithID(
					new ParametersWithRandom(privateKeyParameters, SecureRandom.getInstance("SHA1PRNG")), ida));
			sm2Signer.update(plainText, 0, plainText.length);
		} catch (NoSuchAlgorithmException e) {
		}

		// 生成签名,签名分为两部分r和s,分别对应索引0和1的数组
		return sm2Signer.generateSignature();

	}

	/**
	 * 公钥验签
	 * 
	 * @param publicKey
	 * @param plainText
	 * @param signResult
	 * @return
	 * @throws com.hsm.security.SecurityException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static final boolean verifySign(byte[] publicKey, byte[] plainText, byte[] signResult, byte[] ida)
			throws SecurityException {

		// 获取一条SM2曲线参数
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());
		// 提取公钥点
		ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(publicKey);
		// 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
		ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

		// 创建签名实例
		SM2Signer sm2Signer = new SM2Signer();
		ParametersWithID parametersWithID = new ParametersWithID(publicKeyParameters, ida);
		sm2Signer.init(false, parametersWithID);
		sm2Signer.update(plainText, 0, plainText.length);

		// 验证签名结果
		boolean verify = sm2Signer.verifySignature(signResult);
		return verify;
	}

	public static void main(String[] args)
			throws SecurityException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CryptoException {
//		LocalKeyPair keyPair = generateKeyPair();
//		System.out.println(keyPair.getPrivateKeyString());
//		System.out.println("private key:" + keyPair.getPublicKeyString());
		
		 String publicKey = "04673E3E497C834E03CF9D3B0196855A0533DCAFE11408A9B1AF5103BBBF1BB5862A45104FDCBD30EFD0FCE4EA451AB09BF60F911E6C0E98B0BFC66CDB2A5DD37B";
		 String privateKey = "0C9C891B548F85DA6A58062AB20B0C61DADEF5D2132B44BC369FD77A5F9323EF";
//		 byte[] a = encrypt(ByteUtils.fromHexString(publicKey), "中文".getBytes());
//		 System.out.println("hex密文串："+ByteUtils.toHexString(a));
//		 byte[] b = decrypt(ByteUtils.fromHexString(privateKey), a);
//		 System.out.println("解密后明文hex串："+ByteUtils.toHexString(b));
//		 System.out.println("aa"+new String(b));
//		 
		 for (int i = 0; i <= 10; i++) {
			
			 byte[] aa = signature(ByteUtils.fromHexString(privateKey), "中文".getBytes(), "aa".getBytes());
			 System.out.println("签名hex串："+ByteUtils.toHexString(aa));
			 boolean bb = verifySign(ByteUtils.fromHexString(publicKey), "中文".getBytes(), aa, "aa".getBytes());
			 System.out.println(bb);
		}
		 
	}

}
