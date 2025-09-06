package com.base.framework.admin.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.base.framework.admin.mapper.SysDictMapper;
import com.base.framework.admin.model.dto.dict.DictFormDTO;
import com.base.framework.admin.model.dto.dict.DictQueryDTO;
import com.base.framework.admin.model.entity.SysDictEntity;
import com.base.framework.admin.model.entity.SysMenuEntity;
import com.base.framework.admin.model.vo.SysDictVO;
import com.base.framework.admin.model.vo.SysMenuVO;
import com.base.framework.admin.service.SysDictService;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.DictTreeUtil;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@Service
@Slf4j
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

    /**
     * 字典列表
     * @param params DictQueryDTO
     * @return ResultVo
     */
    @Override
    public ResultVo queryDictPage(DictQueryDTO params){
        params.setPageSizeZero(true);
        params.setPageSize(0);
        List<SysDictEntity> list =
                PageHelper.startPage(params.getPageNo(), params.getPageSize(), params.isCount(), params.isReasonable(), params.isPageSizeZero())
                .doSelectPage(() -> sysDictMapper.queryDictPage(params));
        return ResultVo.ok(new PageInfo<>(DictTreeUtil.buildTree(CglibUtil.copyList(list, SysDictVO::new))));
    }

    @Override
    public ResultVo queryFirstLevelList() {
        List<SysDictEntity> list = sysDictMapper.queryFirstLevelList();
        return ResultVo.ok(CglibUtil.copyList(list, SysDictVO::new));
    }

    @Override
    public List<SysDictVO> queryDictList() {
        List<SysDictEntity> list = sysDictMapper.queryDictPage(new DictQueryDTO());
        List<SysDictVO> sysDictVO = CglibUtil.copyList(list, SysDictVO::new);
        return DictTreeUtil.buildTree(sysDictVO);
    }

    @Override
    @Transactional
    public ResultVo<Long> save(DictFormDTO params){
        SysDictEntity sysDictEntity = sysDictMapper.dictDetailByName(params.getName(), Long.valueOf(params.getParentId()));
        if(sysDictEntity != null) {
            throw new BusinessException(500, "字典名称已存在");
        }
        params.setAccountId(SecurityUtils.getCurrentUserId());
        if(params.getParentId() == null) {
            params.setParentId(0L);
        }
        sysDictMapper.save(params);
        return ResultVo.ok(params.getId());
    }

    @Override
    @Transactional
    public ResultVo<Boolean> update(DictFormDTO params) {
        this.getDictById(params.getId());
        params.setAccountId(SecurityUtils.getCurrentUserId());
        sysDictMapper.update(params);
        return ResultVo.ok(true);
    }

    @Override
    @Transactional
    public ResultVo<Boolean> updateState(DictFormDTO params) {
        this.getDictById(params.getId());
        params.setAccountId(SecurityUtils.getCurrentUserId());
        sysDictMapper.updateState(params);
        return ResultVo.ok(true);
    }

    @Override
    public SysDictVO getDictById(Long id) {
        SysDictEntity sysDictEntity = sysDictMapper.getDictById(id);
        if(sysDictEntity == null) {
            throw new BusinessException(500, "字典不存在");
        }
        SysDictVO sysDictVO = new SysDictVO();
        BeanUtils.copyProperties(sysDictEntity, sysDictVO);
        return sysDictVO;
    }

    private static void collectChildrenIds(List<SysDictEntity> list, Long parentId, List<Long> result, Set<Long> visited) {
        for (SysDictEntity node : list) {
            if (node.getId().equals(parentId) && visited.add(node.getId())) {
                // 添加自身
                result.add(node.getId());
            }
            if (node.getParentId().equals(parentId)) {
                if (visited.add(node.getId())) {
                    result.add(node.getId());
                }
                // 递归查找子节点
                collectChildrenIds(list, node.getId(), result, visited);
            }
        }
    }

    @Override
    @Transactional
    public ResultVo<Boolean> delete(Long id){
        // 判断删除id是否存在
        this.getDictById(id);

        // 查询所有字典数据
        List<SysDictEntity> list = sysDictMapper.queryDictPage(new DictQueryDTO());
        // 防止重复
        Set<Long> visited = new HashSet<>();
        List<Long> ids = new ArrayList<>();
        collectChildrenIds(list, id, ids, visited);
        if(ids.isEmpty()) {
            ids.add(id);
        }
        sysDictMapper.delete(ids);
        return ResultVo.ok(true);
    }

}
