package com.ruoyi.property.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 心风
 * @date 2022/10/08 16:35
 **/
public interface SysFileService {
    AjaxResult saveFile(MultipartFile file);

    AjaxResult removeByIdsAndFiles(List<String> ids);

    AjaxResult saveFiles(List<MultipartFile> files) throws IOException;
}
