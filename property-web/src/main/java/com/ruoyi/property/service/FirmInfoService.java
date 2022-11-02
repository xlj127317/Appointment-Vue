package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.FirmInfo;

/**
 * 企业信息Service接口
 *
 * @author wind
 * @date 2022-11-02
 */
public interface FirmInfoService {
    /**
     * 查询企业信息
     *
     * @param id 企业信息主键
     * @return 企业信息
     */
     FirmInfo selectFirmInfoById(String id);

    /**
     * 查询企业信息列表
     *
     * @param firmInfo 企业信息
     * @return 企业信息集合
     */
    List<FirmInfo> selectFirmInfoList(FirmInfo firmInfo);

    /**
     * 新增企业信息
     *
     * @param firmInfo 企业信息
     * @return 结果
     */
    int insertFirmInfo(FirmInfo firmInfo);

    /**
     * 修改企业信息
     *
     * @param firmInfo 企业信息
     * @return 结果
     */
    int updateFirmInfo(FirmInfo firmInfo);

    /**
     * 批量删除企业信息
     *
     * @param ids 需要删除的企业信息主键集合
     * @return 结果
     */
    int deleteFirmInfoByIds(String[] ids);

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息主键
     * @return 结果
     */
    int deleteFirmInfoById(String id);

    AjaxResult selectFirmInfoByUserId(String userId);
}
