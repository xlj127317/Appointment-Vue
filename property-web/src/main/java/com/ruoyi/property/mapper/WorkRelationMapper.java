package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.WorkRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkRelationMapper {

    /**
     * insert WorkRelation
     *
     * @param workRelation entity
     * @return int
     */
    int insert(WorkRelation workRelation);

    /**
     * select by taskId for WorkRelation
     *
     * @param id taskId
     * @return workRelation
     */
    WorkRelation selectByTaskId(String id);
}