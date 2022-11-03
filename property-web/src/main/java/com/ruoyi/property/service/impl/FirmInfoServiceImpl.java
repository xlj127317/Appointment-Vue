package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.FirmInfoMapper;
import com.ruoyi.property.domain.FirmInfo;
import com.ruoyi.property.service.FirmInfoService;

import javax.annotation.Resource;

/**
 * 企业信息Service业务层处理
 *
 * @author wind
 * @date 2022-11-02
 */
@Service
public class FirmInfoServiceImpl implements FirmInfoService {
    @Resource
    private FirmInfoMapper firmInfoMapper;

    /**
     * 查询企业信息
     *
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public FirmInfo selectFirmInfoById(String id) {
        return firmInfoMapper.selectFirmInfoById(id);
    }

    /**
     * 查询企业信息列表
     *
     * @param firmInfo 企业信息
     * @return 企业信息
     */
    @Override
    public List<FirmInfo> selectFirmInfoList(FirmInfo firmInfo) {
        return firmInfoMapper.selectFirmInfoList(firmInfo);
    }

    /**
     * 新增企业信息
     *
     * @param firmInfo 企业信息
     * @return 结果
     */
    @Override
    public int insertFirmInfo(FirmInfo firmInfo) {
        firmInfo.setId(PkeyGenerator.getUniqueString());
        firmInfo.setCreateTime(DateUtils.getNowDate());
        return firmInfoMapper.insertFirmInfo(firmInfo);
    }

    /**
     * 修改企业信息
     *
     * @param firmInfo 企业信息
     * @return 结果
     */
    @Override
    public int updateFirmInfo(FirmInfo firmInfo) {
        return firmInfoMapper.updateFirmInfo(firmInfo);
    }

    /**
     * 批量删除企业信息
     *
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteFirmInfoByIds(String[] ids) {
        return firmInfoMapper.deleteFirmInfoByIds(ids);
    }

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteFirmInfoById(String id) {
        return firmInfoMapper.deleteFirmInfoById(id);
    }

    /**
     * 根据业主id或电话查询企业信息
     *
     * @param userId 业主id或者电话
     * @return ajax
     */
    @Override
    public AjaxResult selectFirmInfoByUserId(String userId) {
        List<FirmInfo> list = firmInfoMapper.selectFirmInfoByUserId(userId);
        return AjaxResult.success(list);
    }
}
