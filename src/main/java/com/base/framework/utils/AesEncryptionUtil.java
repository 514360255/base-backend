package com.base.framework.utils;

import com.base.framework.exception.BusinessException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: 郭郭
 * @Create: 2025/9/12
 * @Description:
 **/
public class AesEncryptionUtil {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "Hgj#bK0Ert5Zz.8s$sgf!%4db7yfMO%@";

    public static String encrypt(String plainText) {
        try {
            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new BusinessException(500, "加密失败");
        }
    }
    public static String decrypt(String encryptedText) {
        try {
            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new BusinessException(500, "解密失败");
        }
    }
}
