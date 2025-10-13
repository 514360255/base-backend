package com.base.framework.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public class WxPhoneDecryptorUtil {

    /**
     * 解密微信返回的手机号数据
     * @param encryptedData 包括敏感数据的加密数据
     * @param iv 加密算法的初始向量
     * @param sessionKey 会话密钥
     * @return 解密后的 JSON 字符串（包含 phoneNumber, purePhoneNumber, countryCode 等）
     */
    public static String decryptPhone(String encryptedData, String iv, String sessionKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(sessionKey);
            byte[] ivBytes = Base64.getDecoder().decode(iv);
            byte[] dataBytes = Base64.getDecoder().decode(encryptedData);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decrypted = cipher.doFinal(dataBytes);

            // 👇 检查最后一个字节是否是合法的 PKCS#7 填充
            int paddingLength = decrypted[decrypted.length - 1] & 0xFF;

            // ✅ 只有在 1-16 范围内才认为是填充
            if (paddingLength >= 1 && paddingLength <= 16) {
                // 可选：验证填充字节是否一致
                for (int i = 0; i < paddingLength; i++) {
                    if ((decrypted[decrypted.length - 1 - i] & 0xFF) != paddingLength) {
                        throw new RuntimeException("填充字节不一致，数据可能被篡改");
                    }
                }
                byte[] result = new byte[decrypted.length - paddingLength];
                System.arraycopy(decrypted, 0, result, 0, result.length);
                return new String(result, StandardCharsets.UTF_8);
            } else {
                // ❌ 填充长度非法，说明解密失败，直接返回原始解密内容（用于调试）
                String likelyJson = new String(decrypted, StandardCharsets.UTF_8).trim();
                // 尝试提取完整 JSON（如果只是填充问题）
                int start = likelyJson.indexOf('{');
                int end = likelyJson.lastIndexOf('}');
                if (start != -1 && end != -1 && end > start) {
                    return likelyJson.substring(start, end + 1);
                }
                throw new RuntimeException("解密结果无效，填充长度=" + paddingLength + "，可能是 session_key 或 iv 错误");
            }

        } catch (Exception e) {
            throw new RuntimeException("手机号解密失败: " + e.getMessage(), e);
        }
    }
}
