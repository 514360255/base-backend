package com.base.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.base.framework.utils.JwtTokenUtils;
import com.base.framework.utils.SnowflakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.base.framework.constant.JwtConstant.HEADER_TOKEN;

/**
 * @author 郭郭
 * @date 2023/11/10 14:43
 * @DESCRIPTION
 */
@Slf4j
@Component
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    public static String getCurrentUserName() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return JwtTokenUtils.getUsername(httpServletRequest.getHeader(HEADER_TOKEN));
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            log.info("insertFill...");
            this.strictInsertFill(metaObject, "id", Long.class, SnowflakeUtils.creatNo());
            this.strictInsertFill(metaObject, "createBy", String.class, getCurrentUserName());
            this.strictInsertFill(metaObject, "createAt", Date.class, new Date());
            this.strictInsertFill(metaObject, "isDeleted", int.class, 0);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("自动注入失败：{}" + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.strictUpdateFill(metaObject, "modifyBy", String.class, getCurrentUserName());
            this.strictUpdateFill(metaObject, "modifyAt", Date.class, new Date());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("自动注入失败：{}" + e.getMessage());
        }
    }

    /**
     * 新增批量填充
     * @param entityList
     */
    public void batchInsertFill(List<?> entityList) {
        for (Object entity : entityList) {
            MetaObject metaObject = SystemMetaObject.forObject(entity);
            this.insertFill(metaObject);
        }
    }

}
