package com.base.framework.admin.controller;

import com.base.framework.admin.service.FileService;
import com.base.framework.admin.model.dto.file.UploadFileRequest;

import com.base.framework.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.base.framework.constant.RouteConstant.ADMIN_PREFIX;

/**
 * 文件接口
 * @author guojiuling
 */
@RestController
@RequestMapping(ADMIN_PREFIX + "file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     */
    @PostMapping("upload/img")
    public ResultVo uploadFile(@RequestPart("file") MultipartFile multipartFile, UploadFileRequest uploadFileRequest) {
        return fileService.upload(multipartFile, uploadFileRequest);
    }
}
