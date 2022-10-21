package com.tinet.clink.openapi.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具封装类
 *
 * @author wangll
 * @date 2020/04/29
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  EncryptUtil {

    /**
     * 包装AES加密方法，将标准AES加密后的密文进行二次加密
     *
     * @param encryptedText 需要加密的文本
     * @param password      加密密码
     * @return 加密后的文本
     */
    public static String aesEncrypt(String encryptedText, String password) throws NoSuchPaddingException,
            BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException,
            InvalidKeyException {

        if (encryptedText == null || "".equals(encryptedText)) {
            return encryptedText;
        }

        encryptedText = AesUtil.encrypt(encryptedText, password);
        Charset charset = Charset.forName("UTF-8");
        encryptedText = "T" + Base64.encodeBase64String(encryptedText.getBytes(charset));

        return encryptedText;
    }

    /**
     * 包装AES解密方法
     *
     * @param encryptedText 需解密的文本
     * @param password      加密密码
     * @return 解密后的文本
     */
    public static String aesDecrypt(String encryptedText, String password) throws IllegalBlockSizeException,
            NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        if (encryptedText == null || !encryptedText.startsWith("T")) {
            return encryptedText;
        }

        encryptedText = encryptedText.substring(1);
        Charset charset = Charset.forName("UTF-8");
        encryptedText = new String(Base64.decodeBase64(encryptedText.getBytes(charset)), charset);

        return AesUtil.decrypt(encryptedText, password);
    }
}
