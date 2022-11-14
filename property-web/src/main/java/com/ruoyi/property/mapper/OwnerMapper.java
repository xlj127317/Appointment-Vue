package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Owner;
import com.ruoyi.property.vo.resp.OwnerSettledResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OwnerMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Owner record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Owner selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Owner record);

    /**
     * 查询业主管理
     *
     * @param id 业主管理主键
     * @return 业主管理
     */
    Owner selectOwnerById(String id);

    /**
     * 查询业主管理列表
     *
     * @param owner 业主管理
     * @return 业主管理集合
     */
    List<Owner> selectOwnerList(Owner owner);

    /**
     * 新增业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    int insertOwner(Owner owner);

    /**
     * 修改业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    int updateOwner(Owner owner);

    /**
     * 删除业主管理
     *
     * @param id 业主管理主键
     * @return 结果
     */
    int deleteOwnerById(String id);

    /**
     * 批量删除业主管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOwnerByIds(String[] ids);

    Owner selectOwnerByIDCard(String identity);

    /**
     * 按照月份统计企业入驻量
     *
     * @return list
     */
    List<OwnerSettledResp> getSettled();
}