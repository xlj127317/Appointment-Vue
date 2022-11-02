package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.FirmInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 企业信息Mapper接口
 *
 * @author wind
 * @date 2022-11-02
 */
@Mapper
public interface FirmInfoMapper {
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
     * 删除企业信息
     *
     * @param id 企业信息主键
     * @return 结果
     */
    int deleteFirmInfoById(String id);

    /**
     * 批量删除企业信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFirmInfoByIds(String[] ids);
}
