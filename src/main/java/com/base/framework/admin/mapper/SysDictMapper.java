package com.base.framework.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.framework.admin.model.dto.BaseDTO;
import com.base.framework.admin.model.dto.dict.DictFormDTO;
import com.base.framework.admin.model.dto.dict.DictQueryDTO;
import com.base.framework.admin.model.entity.SysDictEntity;
import com.base.framework.utils.ResultVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
public interface SysDictMapper extends BaseMapper<BaseDTO> {

    /**
     * 查询字典列表
     * @param params DictQueryDTO
     * @return List<SysDictEntity>
     */
    List<SysDictEntity> queryDictPage(DictQueryDTO params);

    /**
     * 保存字典
     * @param params SysDictFormDTO
     * @return Long
     */
    Long save(DictFormDTO params);

    /**
     * 修改字典
     * @param params SysDictFormDTO
     */
    void update(DictFormDTO params);

    /**
     * 修改字典状态
     * @param params SysDictFormDTO
     */
    void updateState(DictFormDTO params);

    /**
     * 根据name获取字典
     * @param name String
     * @return SysDictEntity
     */
    SysDictEntity dictDetailByName(String name, Long id);

    /**
     * 根据id获取字典
     * @param id String
     * @return SysDictEntity
     */
    SysDictEntity getDictById(Long id);

    /**
     * 删除字典
     * @param ids List<Long>
     */
    void delete(@Param("list") List<Long> ids);

}
