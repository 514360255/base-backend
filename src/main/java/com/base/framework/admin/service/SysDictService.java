package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.dict.DictFormDTO;
import com.base.framework.admin.model.dto.dict.DictQueryDTO;
import com.base.framework.admin.model.vo.SysDictVO;
import com.base.framework.utils.ResultVo;

import java.util.List;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
public interface SysDictService {

    /**
     * 查询字典列表
     * @param params DictQueryDTO
     * @return ResultVo
     */
    ResultVo queryDictPage(DictQueryDTO params);

    /**
     * 查询字典列表
     * @return ResultVo
     */
    List<SysDictVO> queryDictList();

    /**
     * 保存字典
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Long> save(DictFormDTO params);

    /**
     * 修改字典
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Boolean> update(DictFormDTO params);

    /**
     * 修改字典状态
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    ResultVo<Boolean> updateState(DictFormDTO params);

    /**
     * 保存字典
     * @param id Long
     * @return ResultVo<Boolean>
     */
    ResultVo<Boolean> delete(Long id);

    /**
     * 保存字典
     * @param id Long
     * @return ResultVo<Boolean>
     */
    SysDictVO getDictById(Long id);

}
