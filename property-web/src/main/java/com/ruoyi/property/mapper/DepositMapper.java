package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Deposit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepositMapper {
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
    int insertSelective(Deposit record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Deposit selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Deposit record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Deposit record);

    /**
     * 查询押金
     *
     * @param id 押金主键
     * @return 押金
     */
    Deposit selectDepositById(String id);

    /**
     * 查询押金列表
     *
     * @param deposit 押金
     * @return 押金集合
     */
    List<Deposit> selectDepositList(Deposit deposit);

    /**
     * 新增押金
     *
     * @param deposit 押金
     * @return 结果
     */
    int insertDeposit(Deposit deposit);

    /**
     * 修改押金
     *
     * @param deposit 押金
     * @return 结果
     */
    int updateDeposit(Deposit deposit);

    /**
     * 删除押金
     *
     * @param id 押金主键
     * @return 结果
     */
    int deleteDepositById(String id);

    /**
     * 批量删除押金
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDepositByIds(String[] ids);

    Deposit get(Map params);
}