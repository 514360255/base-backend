package com.base.framework.utils;

import com.base.framework.admin.model.entity.SysAccount;

/**
 * @Author: 郭郭
 * @Create: 2025/8/8
 * @Description: 用户信息上下文
 **/
public class UserContextUtils {
    private static final ThreadLocal<SysAccount> USER_HOLDER = new ThreadLocal<>();

    public static void setUser(SysAccount user) {
        USER_HOLDER.set(user);
    }

    public static SysAccount getUser() {
        return USER_HOLDER.get();
    }

    public static void clear() {
        USER_HOLDER.remove();
    }
}
