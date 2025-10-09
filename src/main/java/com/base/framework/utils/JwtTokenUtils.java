package com.base.framework.utils;

import com.base.framework.constant.JwtConstant;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author guojiuling
 */
public class JwtTokenUtils {

    private static Claims claims = null;

    /**
     * 生成Token
     */
    public static String createToken(String subject, String userId, String role, String name) {
        return Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JwtConstant.APP_SECRET_KEY).compact();
    }

    public static String getToken(String token) {
        return token.substring(JwtConstant.TOKEN_PREFIX.length() + 1);
    }

    /**
     * 获取Token中的信息 ，解析token
     */
    public static Claims getTokenClaim(String token) {
        // 1. 检查 token 是否为空
        if (Objects.isNull(token) || token.isEmpty()) {
            return null;
        }

        try {
            // 2. 解析 token
            return Jwts.parser()
                    .setSigningKey(JwtConstant.APP_SECRET_KEY)
                    .parseClaimsJws(getToken(token))
                    .getBody();
        }
        catch (ExpiredJwtException e) {
            // ✅ JWT 已过期
            // 返回 null，后续判断为未登录
            return null;
        }
        catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            // ✅ 其他 JWT 格式或签名错误
            return null;
        }
        catch (Exception e) {
            // ✅ 兜底异常
            return null;
        }
    }

    /**
     * 获取Token失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return Objects.requireNonNull(getTokenClaim(token)).getExpiration();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAtDateFromToken(String token) {
        return Objects.requireNonNull(getTokenClaim(token)).getIssuedAt();
    }

    /**
     * 从token中获取userId
     */
    public static Long getUserId(String token) {
        Claims claims = getTokenClaim(token);
        if(claims == null) {
            return null;
        }
        String userId = (String) claims.get("userId");
        if(userId == null) {
            return null;
        }
        return Long.valueOf(userId);
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        try {
            Claims claims = getTokenClaim(token);
            if(claims == null) {
                return true;
            }
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
