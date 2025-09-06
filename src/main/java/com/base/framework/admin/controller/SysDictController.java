package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.dict.DictFormDTO;
import com.base.framework.admin.model.dto.dict.DictQueryDTO;
import com.base.framework.admin.model.vo.SysDictVO;
import com.base.framework.admin.service.SysDictService;
import com.base.framework.utils.ResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "dict")
public class SysDictController {

    @Resource
    SysDictService sysDictService;

    /**
     * 查询字典列表
     * @param params DictQueryDTO
     * @return ResultVo
     */
    @GetMapping("page")
    public ResultVo queryDictPage(@Param("params") DictQueryDTO params) {
        return sysDictService.queryDictPage(params);
    }

    /**
     * 查询所有字典列表
     * @return ResultVo
     */
    @GetMapping("list")
    public ResultVo queryDictList() {
        return ResultVo.ok(sysDictService.queryDictList());
    }

    /**
     * 保存字典
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    @PostMapping
    public ResultVo<Long> save(@RequestBody DictFormDTO params) {
        return sysDictService.save(params);
    }

    /**
     * 修改字典
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    @PutMapping
    public ResultVo<Boolean> update(@RequestBody DictFormDTO params) {
        return sysDictService.update(params);
    }


    /**
     * 修改字典
     * @param params DictFormDTO
     * @return ResultVo<Long>
     */
    @PostMapping("update/state")
    public ResultVo<Boolean> updateState(@RequestBody DictFormDTO params) {
        return sysDictService.updateState(params);
    }

    /**
     * 删除字典
     * @param id Long
     * @return ResultVo<Boolean>
     */
    @DeleteMapping("{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        return sysDictService.delete(id);
    }

    /**
     * 根据id查询字典详情
     * @param id Long
     * @return ResultVo<SysDictVO>
     */
    @GetMapping("{id}")
    public ResultVo<SysDictVO> getDictById(@PathVariable Long id) {
        return ResultVo.ok(sysDictService.getDictById(id));
    }

}
