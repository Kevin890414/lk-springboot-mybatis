
package com.example.demo.utils.security;

/**    
 *
 * @Description: 异或操作类
 * @ClassName:  Xor     
 * @version V1.0 
 * @author: likui     
 * @date: 2021年1月29日 下午3:47:47  
 *
 */ 
public class Xor {
    private Xor() {
    }

    public static final byte xOR(byte b1, byte b2) {
        return (byte)(b1 ^ b2);
    }

    public static final byte[] xOR(byte[] b1, byte[] b2) {
        int resultLength = b1.length >= b2.length ? b2.length : b1.length;
        byte[] result = new byte[resultLength];

        for(int i = 0; i < result.length; ++i) {
            result[i] = xOR(b1[i], b2[i]);
        }

        return result;
    }
}
