package com.base.framework.utils;

import com.base.framework.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Objects;

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
                .claim("username", subject)
                .claim("role", role)
                .claim("userId", userId)
                .claim("name", name)
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
        if(Objects.isNull(token)) {
            return null;
        }
        if(claims == null) {
            claims = Jwts.parser().setSigningKey(JwtConstant.APP_SECRET_KEY).parseClaimsJws(getToken(token)).getBody();
        }
        return claims;
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
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        Claims claims = getTokenClaim(token);
        if(claims == null) {
            return null;
        }
        return claims.get("username").toString();
    }

    public static String getName(String token) {
        Claims claims = getTokenClaim(token);
        if(claims == null) {
            return null;
        }
        return claims.get("name").toString();
    }

    /**
     * 从token中获取角色
     */
    public static String getRole(String token) {
        Claims claims = getTokenClaim(token);
        if(claims == null) {
            return null;
        }
        return claims.get("role").toString();
    }

    /**
     * 从token中获取userId
     */
    public static String getUserId(String token) {
        Claims claims = getTokenClaim(token);
        if(claims == null) {
            return null;
        }
        return claims.get("userId").toString();
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        try {
            Claims claims = getTokenClaim(token);
            if(claims == null) {
                return false;
            }
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 验证令牌
     */
    public static Boolean validateToken(String token) {
        return !isExpiration(token);
    }

}
