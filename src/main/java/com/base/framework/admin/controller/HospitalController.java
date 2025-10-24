package com.base.framework.admin.controller;

import com.base.framework.admin.model.dto.hospital.HospitalFormDTO;
import com.base.framework.admin.model.dto.hospital.HospitalQueryDTO;
import com.base.framework.admin.model.vo.HospitalVO;
import com.base.framework.admin.service.HospitalService;
import com.base.framework.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@RestController
@RequestMapping(ADMIN_PREFIX + "hospital")
@Slf4j
public class HospitalController {

    @Resource
    HospitalService hospitalService;

    /**
     * 医院列表
     * @param params HospitalQueryDTO
     * @return ResultVo
     */
    @GetMapping("page")
    public ResultVo queryHospitalPage(@Param("params") HospitalQueryDTO params) {
        return hospitalService.queryHospitalPage(params);
    }

    /**
     * 保存医院信息
     * @param params HospitalFormDTO
     * @return ResultVo<Long>
     */
    @PostMapping
    public ResultVo<Long> save(@RequestBody HospitalFormDTO params) {
        return hospitalService.save(params);
    }

    /**
     * 获取医院信息
     * @param input String
     * @return ResultVo<HospitalVO>
     */
    @GetMapping("{input}")
    public ResultVo<HospitalVO> getHospitalDetail(@PathVariable("input") String input){
        return ResultVo.ok(hospitalService.getHospitalDetail(input));
    }

    /**
     * 删除医院
     * @param id Long
     * @return ResultVo<Boolean>
     */
    @DeleteMapping("{id}")
    public ResultVo deleteHospital(@PathVariable("id") Long id) {
        return hospitalService.deleteHospital(id);
    }

    /**
     * 修改状态
     * @param params HospitalFormDTO
     * @return ResultVo<Boolean>
     */
    @PostMapping("state")
    public ResultVo<Boolean> updateState(@RequestBody HospitalFormDTO params) {
        return hospitalService.updateState(params);
    }

    /**
     * 修改状态
     * @param params HospitalFormDTO
     * @return ResultVo<Boolean>
     */
    @PutMapping
    public ResultVo<Boolean> update(@RequestBody HospitalFormDTO params) {
        return hospitalService.update(params);
    }

    /**
     * 修改医院secret
     * @param hospitalId Long
     * @param secret String
     * @return ResultVo<Boolean>
     */
    @PutMapping("{hospitalId}/{secret}")
    public ResultVo updateHospitalSecret(@PathVariable("hospitalId") Long hospitalId, @PathVariable("secret") String secret) {
        return hospitalService.updateHospitalSecret(hospitalId, secret);
    }

}
