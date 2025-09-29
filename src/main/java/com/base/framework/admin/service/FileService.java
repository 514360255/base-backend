package com.base.framework.admin.service;

import com.base.framework.admin.model.dto.file.UploadFileRequest;
import com.base.framework.utils.ResultVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 郭郭
 * @Create: 2025/9/18
 * @Description:
 **/
public interface FileService {

    /**
     * 上传文件
     * @param multipartFile MultipartFile
     * @param uploadFileRequest UploadFileRequest
     * @return ResultVo
     */
    ResultVo upload(MultipartFile multipartFile, UploadFileRequest uploadFileRequest);

}
