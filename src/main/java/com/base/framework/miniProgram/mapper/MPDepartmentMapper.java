package com.base.framework.miniProgram.mapper;

import com.base.framework.miniProgram.model.entity.MPDepartmentEntity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public interface MPDepartmentMapper {

    /**
     * 根据ids查询科室列表
     * @param ids
     * @return
     */
    List<MPDepartmentEntity> queryDepartmentList(@Param("ids") List<Long> ids);

}
