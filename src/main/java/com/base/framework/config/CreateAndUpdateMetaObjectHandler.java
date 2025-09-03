package com.base.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;
import com.base.framework.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author 郭郭
 * @date 2023/11/10 14:43
 * @DESCRIPTION
 */
@Slf4j
@Component
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            log.info("insertFill...");
            this.strictInsertFill(metaObject, "createdBy", String.class, SecurityUtils.getCurrentUsername());
            this.strictInsertFill(metaObject, "accountId", Long.class, SecurityUtils.getCurrentUserId());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("自动注入失败：{}" + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            log.info("updateFill...");
            this.strictUpdateFill(metaObject, "updatedBy", String.class, SecurityUtils.getCurrentUsername());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("自动注入失败：{}" + e.getMessage());
        }
    }

}
