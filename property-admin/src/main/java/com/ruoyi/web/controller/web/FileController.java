package com.ruoyi.web.controller.web;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.service.SysFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传管理
 *
 * @author 心风
 * @date 2022/10/08 15:35
 **/
@Api(tags = "文件管理")
@RestController
@RequestMapping("/hcx/property/file")
public class FileController {

    @Autowired
    private SysFileService sysFileService;

    @ApiOperation(value = "单文件上传")
    @PostMapping("/upload")
    public AjaxResult add(@RequestParam(value = "file",required = false) MultipartFile file) {
        //判断文件是否空
        if (file == null || file.getOriginalFilename() == null || "".equalsIgnoreCase(file.getOriginalFilename().trim())) {
            return AjaxResult.error("文件为空");
        }
        return sysFileService.saveFile(file);
    }

    /**
     * 多文件上传
     */
    @ApiOperation(value = "多文件上传")
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
        return sysFileService.saveFiles(files);
    }


    @ApiOperation(value = "删除")
    @GetMapping(value = "/updateByIds")
    public AjaxResult updateById(@RequestParam @ApiParam(value = "id集合") List<String> ids) {
        return sysFileService.removeByIdsAndFiles(ids);
    }
}
