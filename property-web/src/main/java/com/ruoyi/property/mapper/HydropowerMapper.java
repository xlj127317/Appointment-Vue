package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Hydropower;import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface HydropowerMapper {
    /**
     * 查询水电统计
     *
     * @param id 水电统计主键
     * @return 水电统计
     */
    Hydropower selectHydropowerById(String id);

    /**
     * 查询水电统计列表
     *
     * @param hydropower 水电统计
     * @return 水电统计集合
     */
    List<Hydropower> selectHydropowerList(Hydropower hydropower);

    /**
     * 新增水电统计
     *
     * @param hydropower 水电统计
     * @return 结果
     */
    int insertHydropower(Hydropower hydropower);

    /**
     * 修改水电统计
     *
     * @param hydropower 水电统计
     * @return 结果
     */
    int updateHydropower(Hydropower hydropower);

    /**
     * 删除水电统计
     *
     * @param id 水电统计主键
     * @return 结果
     */
    int deleteHydropowerById(String id);

    /**
     * 批量删除水电统计
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHydropowerByIds(String[] ids);
}