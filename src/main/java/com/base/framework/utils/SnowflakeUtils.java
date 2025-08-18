package com.base.framework.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author 郭郭
 * @date 2024/8/15 14:22
 * @DESCRIPTION
 */
public class SnowflakeUtils {

    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    public static Long creatNo() {
        return snowflake.nextId();
    }

}
