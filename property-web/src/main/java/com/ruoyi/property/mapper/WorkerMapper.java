package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface WorkerMapper {
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
    int insertSelective(Worker record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Worker selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Worker record);


    /**
     * 查询用工管理
     *
     * @param id 用工管理主键
     * @return 用工管理
     */
    Worker selectWorkerById(String id);

    /**
     * 查询用工管理列表
     *
     * @param worker 用工管理
     * @return 用工管理集合
     */
    List<Worker> selectWorkerList(Worker worker);

    /**
     * 新增用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    int insertWorker(Worker worker);

    /**
     * 修改用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    int updateWorker(Worker worker);

    /**
     * 删除用工管理
     *
     * @param id 用工管理主键
     * @return 结果
     */
    int deleteWorkerById(String id);

    /**
     * 批量删除用工管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWorkerByIds(String[] ids);

    /**
     * update worker finishStatus
     *
     * @param id           id
     * @param finishStatus 完成状态
     * @param finishDate   完成时间
     * @return int
     */
    int updateFinishStatus(@Param("id") String id, @Param("finishStatus") Integer finishStatus, @Param("finishDate") Date finishDate);
}