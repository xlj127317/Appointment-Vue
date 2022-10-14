package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Report;
import com.ruoyi.property.vo.req.ReportAuditReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
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
    int insertSelective(Report record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Report selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Report record);

    /**
     * 查询工单管理
     *
     * @param id 工单管理主键
     * @return 工单管理
     */
    Report selectReportById(String id);

    /**
     * 查询工单管理列表
     *
     * @param report 工单管理
     * @return 工单管理集合
     */
    List<Report> selectReportList(Report report);

    /**
     * 新增工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    int insertReport(Report report);

    /**
     * 修改工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    int updateReport(Report report);

    /**
     * 删除工单管理
     *
     * @param id 工单管理主键
     * @return 结果
     */
    int deleteReportById(String id);

    /**
     * 批量删除工单管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteReportByIds(String[] ids);

    int updateAuditStatus(ReportAuditReq reportAuditReq);
}