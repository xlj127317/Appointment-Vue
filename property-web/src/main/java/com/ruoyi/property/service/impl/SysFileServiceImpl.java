package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.config.FileUploadProperties;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.SysFiles;
import com.ruoyi.property.mapper.SysFilesMapper;
import com.ruoyi.property.service.SysFileService;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 心风
 * @date 2022/10/08 16:35
 **/
@Service
public class SysFileServiceImpl implements SysFileService {

    @Resource
    private FileUploadProperties fileUploadProperties;

    @Resource
    private SysFilesMapper sysFilesMapper;

    private static final String FILE_DELIMETER = ",";

    /**
     * 文件名称分隔符
     */
    private final static String SEGMENTATION = ".";

    @Override
    public AjaxResult saveFile(MultipartFile file) {
        //存储文件夹
        String createTime = DateUtils.format(new Date(), "yyyyMMdd");
        String newPath = fileUploadProperties.getPath() + createTime + File.separator;
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        try {
            String fileName = file.getOriginalFilename();
            //id与filename保持一直，删除文件
            String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
            String newFilePathName = newPath + fileNameNew;
            String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileNameNew;
            //创建输出文件对象
            File outFile = new File(newFilePathName);
            //拷贝文件到输出文件对象
            FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
            SysFiles sysFiles = new SysFiles();
            String uniqueId = PkeyGenerator.getUniqueString();
            sysFiles.setId(uniqueId);
            sysFiles.setFileName(fileName);
            sysFiles.setFilePath(newFilePathName);
            sysFiles.setUrl(url);
            sysFiles.setIsDelete(0);
            sysFiles.setCreateId("1");
            sysFiles.setCreateDate(DateUtil.date(CalendarUtil.calendar()));
            sysFiles.setUpdateTime(DateUtil.date(CalendarUtil.calendar()));
            sysFilesMapper.insertSelective(sysFiles);
            Map<String, String> resultMap = new ConcurrentHashMap<>(10);
            resultMap.put("src", url);
            resultMap.put("fileId", uniqueId);
            resultMap.put("fileName", fileName);
            return AjaxResult.success(resultMap);
        } catch (Exception e) {
            throw new GlobalException("上传文件失败");
        }
    }

    @Override
    public AjaxResult removeByIdsAndFiles(List<String> ids) {
        List<SysFiles> list = sysFilesMapper.queryByIds(ids);
        list.forEach(entity -> {
            //如果之前的文件存在，删除
            File file = new File(entity.getFilePath());
            if (file.exists()) {
                file.delete();
            }
        });
        int update = sysFilesMapper.updateByIds(ids);
        return AjaxResult.success(update);
    }

    @Override
    public AjaxResult saveFiles(List<MultipartFile> files) {

        List<String> urls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        List<String> newFileNames = new ArrayList<>();
        List<String> originalFilenames = new ArrayList<>();
        List<String> uploadsFileNum = new ArrayList<>();

        //存储文件夹
        String createTime = DateUtils.format(new Date(), "yyyyMMdd");
        String newPath = fileUploadProperties.getPath() + createTime + File.separator;
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        for (MultipartFile file : files) {
            try {
                String fileName = file.getOriginalFilename();
                //id与filename保持一直，删除文件
                String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
                String newFilePathName = newPath + fileNameNew;
                String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileNameNew;
                //创建输出文件对象
                File outFile = new File(newFilePathName);
                //拷贝文件到输出文件对象
                FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                SysFiles sysFiles = new SysFiles();
                String uniqueId = PkeyGenerator.getUniqueString();
                sysFiles.setId(uniqueId);
                sysFiles.setFileName(fileName);
                sysFiles.setFilePath(newFilePathName);
                sysFiles.setUrl(url);
                sysFiles.setCreateId("1");
                sysFiles.setIsDelete(0);
                sysFiles.setCreateDate(DateUtil.date(CalendarUtil.calendar()));
                sysFiles.setUpdateTime(DateUtil.date(CalendarUtil.calendar()));
                int num = sysFilesMapper.insertSelective(sysFiles);
                num++;
                uploadsFileNum.add(String.valueOf(num));
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(fileNameNew);
                originalFilenames.add(file.getOriginalFilename());
            } catch (Exception e) {
                throw new GlobalException("上传文件失败");
            }
        }
        AjaxResult ajax = new AjaxResult();
        ajax.put("uploadsFileNum", StringUtils.join(uploadsFileNum, FILE_DELIMETER));
        ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
        ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
        ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
        ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
        return ajax;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.contains(SEGMENTATION)) {
            return fileName.substring(fileName.lastIndexOf(SEGMENTATION));
        }
        return "";
    }
}
