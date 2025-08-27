package com.base.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;
import com.base.framework.utils.JwtTokenUtils;
import com.base.framework.utils.SecurityUtils;
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
        CustomUserDetailsVO userDetailsVO = SecurityUtils.getCurrentUser();
        if(userDetailsVO != null && userDetailsVO.getName() != null) {
            return userDetailsVO.getName();
        }
        return null;
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            log.info("insertFill...");
            this.strictInsertFill(metaObject, "id", Long.class, SnowflakeUtils.creatNo());
            this.strictInsertFill(metaObject, "createdBy", String.class, getCurrentUserName());
            this.strictInsertFill(metaObject, "createdAt", Date.class, new Date());
            this.strictInsertFill(metaObject, "isDeleted", int.class, 1);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("自动注入失败：{}" + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.strictUpdateFill(metaObject, "updatedBy", String.class, getCurrentUserName());
            this.strictUpdateFill(metaObject, "updatedAt", Date.class, new Date());
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
