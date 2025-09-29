package com.base.framework.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import com.base.framework.admin.model.dto.file.UploadFileRequest;
import com.base.framework.admin.model.vo.CustomUserDetailsVO;
import com.base.framework.admin.service.FileService;
import com.base.framework.common.ErrorCode;
import com.base.framework.config.UploadConfig;
import com.base.framework.exception.BusinessException;
import com.base.framework.utils.ResultVo;
import com.base.framework.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Author: 郭郭
 * @Create: 2025/9/18
 * @Description:
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private UploadConfig uploadConfig;

    // 1MB
    private static final long MAX_FILE_SIZE = 1024 * 1024;
    private static final Set<String> ALLOWED_IMAGE_SUFFIXES = new HashSet<>(Arrays.asList("jpeg", "jpg", "svg", "png", "webp"));

    /**
     * 获取文件扩展名
     * @param originalFilename 原始文件名
     * @return 文件扩展名（不包含点号）
     */
    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < originalFilename.length() - 1) {
            return originalFilename.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }

    @Override
    public ResultVo upload(MultipartFile multipartFile, UploadFileRequest uploadFileRequest) {

        validFile(multipartFile);

        CustomUserDetailsVO loginUser = SecurityUtils.getCurrentUser();
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        // 文件目录：用户来划分
        String userHome = System.getProperty("user.home");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileExtension = getFileExtension(multipartFile.getOriginalFilename());
        String filename = uuid + (fileExtension.isEmpty() ? "" : "." + fileExtension);
        Path filePath = Paths.get(userHome, uploadConfig.getUrl(), String.valueOf(loginUser.getId()), filename);


        try {
            Files.createDirectories(filePath.getParent());
            multipartFile.transferTo(filePath);
            String filePathStr = "/uploads/" + loginUser.getId() + "/" + filename;
            HashMap<String, String> result = new HashMap<>();
            result.put("url", filePathStr);
            result.put("uid", UUID.randomUUID().toString());
            result.put("originalName", multipartFile.getOriginalFilename());
            // 返回可访问地址
            return ResultVo.ok(result);
        } catch (IOException e) {
            log.error("file upload IO error, filename = {}", filename, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件上传失败");
        } catch (Exception e) {
            log.error("file upload error, filename = {}", filename, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        }
    }

    /**
     * 校验文件
     * @param multipartFile 文件
     */
    private void validFile(MultipartFile multipartFile) {
        long fileSize = multipartFile.getSize();
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        if (fileSuffix != null) {
            fileSuffix = fileSuffix.toLowerCase();
        }

        if (fileSize > MAX_FILE_SIZE) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过 1M");
        }
        if (!ALLOWED_IMAGE_SUFFIXES.contains(fileSuffix)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件类型错误");
        }
    }
}
