package com.example.demo.utils.security;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;


/**    
 *
 * @Description: sm4加解密工具类
 * @ClassName:  Sm4Util     
 * @version V1.0 
 * @author: likui     
 * @date: 2021年1月29日 上午9:29:56  
 *
 */ 
public class Sm4Util {

    /**
     * 加密标识
     */
    private static final int ENCRYPT = 1;
    /**
     * 解密标识
     */
    private static final int DECRYPT = 0;
    
    /**
     * F轮函数循环次数
     */
    private static final int ROUND = 32;
    /**
     * 在SM4加密算法中 , 要求原始数据长度必须是长度为32的整数倍hex串（即16个字节整数倍） , 但是在实际情况中数据长度并不能保证这么长 , 这里就涉及到了原始数据后填充一个0x00和多个0x00来解决数据长度填充的问题。
	 *	“0X00” 代表十进制中的0，十进制中的0对应ASCII码中的字符null,可以用“\0”代替。加了\表示后面的0是一个转义字符，要作特殊处理，这个转义的意思就是0与原来的字符0的含义不再一样啦，当编译器遇到这个\0时会自动将\0转
	 *	化为十进制0存储在ch中，十进制0对应的ASCII码中的字符即是NULL.
     */
    private static final int BLOCK = 16; 
    private static final int FOUR = 4;
    private static final int ZERO = 0;

    /**
     * 固定 s盒
     */
    private byte[] sbox = {(byte) 0xd6, (byte) 0x90, (byte) 0xe9, (byte) 0xfe,
            (byte) 0xcc, (byte) 0xe1, 0x3d, (byte) 0xb7, 0x16, (byte) 0xb6,
            0x14, (byte) 0xc2, 0x28, (byte) 0xfb, 0x2c, 0x05, 0x2b, 0x67,
            (byte) 0x9a, 0x76, 0x2a, (byte) 0xbe, 0x04, (byte) 0xc3,
            (byte) 0xaa, 0x44, 0x13, 0x26, 0x49, (byte) 0x86, 0x06,
            (byte) 0x99, (byte) 0x9c, 0x42, 0x50, (byte) 0xf4, (byte) 0x91,
            (byte) 0xef, (byte) 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43,
            (byte) 0xed, (byte) 0xcf, (byte) 0xac, 0x62, (byte) 0xe4,
            (byte) 0xb3, 0x1c, (byte) 0xa9, (byte) 0xc9, 0x08, (byte) 0xe8,
            (byte) 0x95, (byte) 0x80, (byte) 0xdf, (byte) 0x94, (byte) 0xfa,
            0x75, (byte) 0x8f, 0x3f, (byte) 0xa6, 0x47, 0x07, (byte) 0xa7,
            (byte) 0xfc, (byte) 0xf3, 0x73, 0x17, (byte) 0xba, (byte) 0x83,
            0x59, 0x3c, 0x19, (byte) 0xe6, (byte) 0x85, 0x4f, (byte) 0xa8,
            0x68, 0x6b, (byte) 0x81, (byte) 0xb2, 0x71, 0x64, (byte) 0xda,
            (byte) 0x8b, (byte) 0xf8, (byte) 0xeb, 0x0f, 0x4b, 0x70, 0x56,
            (byte) 0x9d, 0x35, 0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, (byte) 0xd1,
            (byte) 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, (byte) 0x87,
            (byte) 0xd4, 0x00, 0x46, 0x57, (byte) 0x9f, (byte) 0xd3, 0x27,
            0x52, 0x4c, 0x36, 0x02, (byte) 0xe7, (byte) 0xa0, (byte) 0xc4,
            (byte) 0xc8, (byte) 0x9e, (byte) 0xea, (byte) 0xbf, (byte) 0x8a,
            (byte) 0xd2, 0x40, (byte) 0xc7, 0x38, (byte) 0xb5, (byte) 0xa3,
            (byte) 0xf7, (byte) 0xf2, (byte) 0xce, (byte) 0xf9, 0x61, 0x15,
            (byte) 0xa1, (byte) 0xe0, (byte) 0xae, 0x5d, (byte) 0xa4,
            (byte) 0x9b, 0x34, 0x1a, 0x55, (byte) 0xad, (byte) 0x93, 0x32,
            0x30, (byte) 0xf5, (byte) 0x8c, (byte) 0xb1, (byte) 0xe3, 0x1d,
            (byte) 0xf6, (byte) 0xe2, 0x2e, (byte) 0x82, 0x66, (byte) 0xca,
            0x60, (byte) 0xc0, 0x29, 0x23, (byte) 0xab, 0x0d, 0x53, 0x4e, 0x6f,
            (byte) 0xd5, (byte) 0xdb, 0x37, 0x45, (byte) 0xde, (byte) 0xfd,
            (byte) 0x8e, 0x2f, 0x03, (byte) 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b,
            0x51, (byte) 0x8d, 0x1b, (byte) 0xaf, (byte) 0x92, (byte) 0xbb,
            (byte) 0xdd, (byte) 0xbc, 0x7f, 0x11, (byte) 0xd9, 0x5c, 0x41,
            0x1f, 0x10, 0x5a, (byte) 0xd8, 0x0a, (byte) 0xc1, 0x31,
            (byte) 0x88, (byte) 0xa5, (byte) 0xcd, 0x7b, (byte) 0xbd, 0x2d,
            0x74, (byte) 0xd0, 0x12, (byte) 0xb8, (byte) 0xe5, (byte) 0xb4,
            (byte) 0xb0, (byte) 0x89, 0x69, (byte) 0x97, 0x4a, 0x0c,
            (byte) 0x96, 0x77, 0x7e, 0x65, (byte) 0xb9, (byte) 0xf1, 0x09,
            (byte) 0xc5, 0x6e, (byte) 0xc6, (byte) 0x84, 0x18, (byte) 0xf0,
            0x7d, (byte) 0xec, 0x3a, (byte) 0xdc, 0x4d, 0x20, 0x79,
            (byte) 0xee, 0x5f, 0x3e, (byte) 0xd7, (byte) 0xcb, 0x39, 0x48};


    /**
     * 固定的ck参数
     */
    private int[] ck = {0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9, 0xe0e7eef5,
            0xfc030a11, 0x181f262d, 0x343b4249, 0x50575e65, 0x6c737a81,
            0x888f969d, 0xa4abb2b9, 0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d,
            0x141b2229, 0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209, 0x10171e25,
            0x2c333a41, 0x484f565d, 0x646b7279};

    /**
     * 固定的fk参数
     */
    private int[] fk = {0xa3b1bac6, 0x56aa3350, 0x677d9197, 0xb27022dc};


    /**
     * @Description 将传入的x参数循环左移num位
     * @Author tanp
     */
    private int leftMove(int x, int num) {
        //假如数据为无符号的数，长度为N，需要循环移动n位。可以用下面的公式：
        //循环左移n位： (x>>(N - n) ) | (x<<n)；
        return x << num | x >>> (32 - num);
    }

    /**
     * @Description s盒的非线性变化函数
     * @Author tanp
     */
    private int byteSub(int x) {
        return (sbox[x >>> 24 & 0xFF] & 0xFF) << 24
                | (sbox[x >>> 16 & 0xFF] & 0xFF) << 16
                | (sbox[x >>> 8 & 0xFF] & 0xFF) << 8
                | (sbox[x & 0xFF] & 0xFF);
    }

    /**
     * @Description 轮函数中T函数的线性变化L函数
     * @Author tanp
     */
    private int transLf(int x) {
        //B^(B<<2|B>>>30)^(B<<10|B>>>22)^(B<<18|B>>>14)^(B<<24|B>>>8);
        return x ^ leftMove(x, 2) ^ leftMove(x, 10) ^ leftMove(x, 18) ^ leftMove(x, 24);
    }

    /**
     * @Description 秘钥扩展算法中的T'函数的线性变化L函数
     * @Author tanp
     */
    private int transTf(int x) {
        // B^(B<<13|B>>>19)^(B<<23|B>>>9);
        return x ^ leftMove(x, 13) ^ leftMove(x, 23);
    }

    /**
     * @Description 秘钥扩展算法T置换
     * @Author tanp
     */
    private int transTh(int in) {
        return transTf(byteSub(in));
    }

    /**
     * @Description 轮函数T置换
     * @Author tanp
     */
    private int transLth(int in) {
        return transLf(byteSub(in));
    }

    /**   
     * @Title: sm4KeyExt   
     * @Description: 获取轮秘钥
     * @param: key		秘钥	
     * @param: cryptFlag 加解密标识
     * @return: int[]      
     * @throws   
     */  
    private int[] sm4KeyExt(byte[] key, int cryptFlag) {
        int r, mid;
        int[] x = new int[4];
        int[] tmp = new int[4];
        int[] rk = new int[ROUND];
        getValue(key, x, tmp);
        //获取轮秘钥之前，先将秘钥与系统函数FK异或
        for (r = ZERO; r < FOUR; r++) {
            x[r] = x[r] ^ (fk[r]);
        }
        //轮询执行T置换
        for (r = ZERO; r < ROUND; r += FOUR) {
            rk[r] = x[0] ^= transTh(x[1] ^ x[2] ^ x[3] ^ ck[r]);
            rk[r + 1] = x[1] ^= transTh(x[2] ^ x[3] ^ x[0] ^ ck[r + 1]);
            rk[r + 2] = x[2] ^= transTh(x[3] ^ x[0] ^ x[1] ^ ck[r + 2]);
            rk[r + 3] = x[3] ^= transTh(x[0] ^ x[1] ^ x[2] ^ ck[r + 3]);
        }

        // 解密时轮密钥使用顺序：rk31,rk30,...,rk0
        if (cryptFlag == DECRYPT) {
            for (r = 0; r < BLOCK; r++) {
                mid = rk[r];
                rk[r] = rk[31 - r];
                rk[31 - r] = mid;
            }
        }
        return rk;
    }


    /**
     * @Description 轮函数F
     * @param input 加密解密的字节数组
     * @param rk 轮秘钥
     * @return: void     
     * @throws   
     */
    private void sms4Crypt(byte[] input, byte[] output, int[] rk) {
        int r;
        int[] x = new int[4];
        int[] tmp = new int[4];
        getValue(input, x, tmp);
        for (r = ZERO; r < ROUND; r += FOUR) {
            x[0] = x[0] ^ transLth(x[1] ^ x[2] ^ x[3] ^ rk[r]);
            x[1] = x[1] ^ transLth(x[2] ^ x[3] ^ x[0] ^ rk[r + 1]);
            x[2] = x[2] ^ transLth(x[3] ^ x[0] ^ x[1] ^ rk[r + 2]);
            x[3] = x[3] ^ transLth(x[0] ^ x[1] ^ x[2] ^ rk[r + 3]);
        }
        // Reverse
        for (int j = ZERO; j < BLOCK; j += FOUR) {
            output[j] = (byte) (x[3 - j / 4] >>> 24 & 0xFF);
            output[j + 1] = (byte) (x[3 - j / 4] >>> 16 & 0xFF);
            output[j + 2] = (byte) (x[3 - j / 4] >>> 8 & 0xFF);
            output[j + 3] = (byte) (x[3 - j / 4] & 0xFF);
        }
    }

    private void getValue(byte[] input, int[] x, int[] tmp) {
        for (int i = ZERO; i < FOUR; i++) {
            tmp[0] = input[4 * i] & 0xff;
            tmp[1] = input[1 + 4 * i] & 0xff;
            tmp[2] = input[2 + 4 * i] & 0xff;
            tmp[3] = input[3 + 4 * i] & 0xff;
            x[i] = tmp[0] << 24 | tmp[1] << 16 | tmp[2] << 8 | tmp[3];
        }
    }


    /**
     * @Description 加密、 解密方法
     * @param in        需加密或者解密的字节数组
     * @param inLen     加密解密的字节长度
     * @param key       秘钥
     * @param out       加密解密后的字节数组
     * @param cryptFlag 加解密标识
     * @return: byte[]     
     * @throws
     */
    private byte[] sms4(byte[] in, int inLen, byte[] key, byte[] out, int cryptFlag) {
        int point = 0;
        //获取轮秘钥
        int[] roundKey = sm4KeyExt(key, cryptFlag);
        byte[] input;
        byte[] output = new byte[16];

        while (inLen >= BLOCK) {
            input = Arrays.copyOfRange(in, point, point + 16);
            //每16个字节执行一次轮函数
            sms4Crypt(input, output, roundKey);
            System.arraycopy(output, 0, out, point, BLOCK);
            inLen -= BLOCK;
            point += BLOCK;
        }
        return out;
    }

    /**
     * @Description  ：字符串加密
     * @param plaintext 明文
     * @param key       秘钥
     * @return： String 加密后的明文字符串
     */
    public static String encodeSms4ToHex(String plaintext, byte[] key) {
        if (plaintext == null || plaintext.length() <= 0) {
            return null;
        }
        StringBuilder plaintextBuilder = new StringBuilder(plaintext);
        for (int i = plaintextBuilder.toString().getBytes().length % BLOCK; i < BLOCK; i++) {
            plaintextBuilder.append('\0');
        }
        plaintext = plaintextBuilder.toString();
        return bytesToHexString(encodeSms4(plaintext.getBytes(), key));
    }

    /**
     * 字符串形式的密文解密成明文
     *
     * @param enHex 密文
     * @param key   秘钥
     * @return 解密后的明文
     * @Author tanp
     */
    public static String decodeSms4HexToString(String enHex, byte[] key) {
        byte[] plaintext = decodeSms4(hexStringToBytes(enHex),key);
        return new String(plaintext).trim();
    }


    /**
     * SMS4加密，加密字符数组
     *
     * @param plaintext 字节数组形式的明文
     * @param key       秘钥
     * @return 明文加密后的字接数组
     */
    private static byte[] encodeSms4(byte[] plaintext, byte[] key) {
        byte[] ciphering = new byte[plaintext.length];
        int k = 0;
        int plainLen = plaintext.length;
        while (k + BLOCK <= plainLen) {
            byte[] cellPlain = new byte[16];
            System.arraycopy(plaintext, k, cellPlain, 0, 16);
            byte[] cellCipher = encode16(cellPlain, key);
            System.arraycopy(cellCipher, 0, ciphering, k, cellCipher.length);
            k += 16;
        }
        return ciphering;
    }

    /**
     * 不限明文长度的SMS4解密
     *
     * @param ciphering 需要解密的字节数组
     * @param key 秘钥
     * @return 解密后的字节数组
     */
    private static byte[] decodeSms4(byte[] ciphering, byte[] key) {
        byte[] plaintext = new byte[ciphering.length];
        int k = 0;
        int cipherLen = ciphering.length;
        while (k + BLOCK <= cipherLen) {
            byte[] cellCipher = new byte[16];
            System.arraycopy(ciphering, k, cellCipher, 0, 16);
            byte[] cellPlain = decode16(cellCipher, key);
            System.arraycopy(cellPlain, 0, plaintext, k, cellPlain.length);
            k += BLOCK;
        }

        return plaintext;
    }



    /**
     * 只加密16位明文
     *
     * @param plaintext 明文字节数组
     * @param key       秘钥
     * @return 加密后的字节数组
     */
    private static byte[] encode16(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[16];
        Sm4Util sm4 = new Sm4Util();
        //调用加密方法
        return sm4.sms4(plaintext, 16, key, cipher, ENCRYPT);
    }

    /**
     * 只解密16位密文
     *
     * @param ciphering 需解密的密文
     * @param key 秘钥
     * @return 解密后的明文字节
     */
    private static byte[] decode16(byte[] ciphering, byte[] key) {
        byte[] plain = new byte[16];
        Sm4Util sm4 = new Sm4Util();
        sm4.sms4(ciphering, 16, key, plain, DECRYPT);
        return plain;
    }

    /**
     * 只加密32位明文
     *
     * @param plaintext 明文字节数组
     * @param key       秘钥
     * @return 加密后的字节数组
     */
    private static byte[] encode32(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[32];
        Sm4Util sm4 = new Sm4Util();
        return sm4.sms4(plaintext, 32, key, cipher, ENCRYPT);
    }

    /**
     * 只解密32位密文
     *
     * @param ciphering 需解密的密文
     * @param key 秘钥
     * @return 解密后的明文字节
     */
    private static byte[] decode32(byte[] ciphering, byte[] key) {
        byte[] plain = new byte[32];
        Sm4Util sm4 = new Sm4Util();
        sm4.sms4(ciphering, 32, key, plain, DECRYPT);
        return plain;
    }

    /**
     * 字节数组转字符串
     *
     * @param src 字节数组
     * @return String
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串转字节数组
     *
     * @param hexString the hex string
     * @return byte[]
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.length() <= 0) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    /**
     * @Description 随机获取中文
     * @Date 2020/5/9 14:17
     * @Author tanp
     */
    private static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }
    
    
    static final byte[] HEX_TABLE = {0,0x1,0x2,0x3,0x4,0x5,0x6,0x7,0x8,0x9,0xA,0xB,0xC,0xD,0xE,0xF};  
    static final char[] HEX_CHAR_TABLE = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};  
    private static byte[] hexToByte(String hexString){  
        if(hexString==null || hexString.length()==0)return null;  
        if(hexString.length()%2!=0)throw new RuntimeException();  
        byte[] data = new byte[hexString.length()/2];  
        char[] chars = hexString.toCharArray();  
        for(int i=0;i<hexString.length();i=i+2){  
            data[i/2] = (byte)(HEX_TABLE[getHexCharValue(chars[i])]<<4 | HEX_TABLE[getHexCharValue(chars[i+1])]);  
//            System.out.println(""+data[i/2]);
        }  
        return data;  
    }  
    private static int getHexCharValue(char c){  
        int index = 0;  
        for(char c1: HEX_CHAR_TABLE){  
            if(c==c1){  
                return index;  
            }  
            index++;
        }
        return 0;
    }

    public static byte toByte(char c){
    	   byte b = (byte)"0123456789ABCDEF".indexOf(c);
    	   return b;
    }
    
	public static boolean isEmptyStr(String str) {
		return str == null || str.trim().length() == 0;
	}

    public static void main(String[] args) {
        String keyStr = "0123456789ABCDEFFEDCBA9876543210";
        String msgPlain= "1234567890000000";
		String dstData = "689A1E27F2716D7E556E3409858FCC4FE7823063C12C2A954E0F4B3B7A8EA3E7BD6CD25574208F4D98B6FC27CDA5B3570AA572E9AC977B4FF488CAE7FAE308DF002A8A4EFA863CCAD024AC0300BB40D2";
		
        hexToByte(keyStr);
        System.out.println("加密前：" + msgPlain);
        String enStr =encodeSms4ToHex(msgPlain, ByteHexUtil.hexStringToBytes(keyStr));
        System.out.println("加密后：" + enStr);
        System.out.println("解密后：" + decodeSms4HexToString(enStr, hexToByte(keyStr)));
        System.out.println(dstData+"\n解密后：" + decodeSms4HexToString(dstData, hexToByte(keyStr)) +"612422199305062056".equals(decodeSms4HexToString(enStr, hexToByte(keyStr))));
        
        
        String hexStringToString = ByteHexUtil.hexStringToString("30", 2);
        System.out.println("---"+hexStringToString+"---");
    }


}