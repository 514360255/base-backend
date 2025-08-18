package com.base.framework.constant;

/**
 * @author 郭郭
 * @date 2023/11/7 14:22
 * @DESCRIPTION
 */
public class JwtConstant {

    public static final String APP_SECRET_KEY = "3hN:&Ez59ikteuYwshvln)RQ?6)MKKi-44!BO>aFwJiBJc~w<maP%970gM*fz>B!";
    // 过期时间
    public static final int EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    public static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_TOKEN = "C-Token";

    public static final String USER_NAME = "C-UserName";

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "C-Ez59ikteuYwshvln";

}
