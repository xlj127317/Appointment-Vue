package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 业主管理表
 */
@ApiModel(value = "业主管理表")
@Schema
public class Owner extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Schema(description = "姓名")
    private String username;

    /**
     * 性别，0：男，1：女，2：未知
     */
    @ApiModelProperty(value = "性别，0：男，1：女，2：未知")
    @Schema(description = "性别，0：男，1：女，2：未知")
    private Integer gender;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Schema(description = "身份证号")
    private String identity;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Schema(description = "电话")
    private String phone;

    /**
     * 业主状态 默认0；0：有效；1：失效
     */
    @ApiModelProperty(value = "业主状态 默认0；0：有效；1：失效")
    @Schema(description = "业主状态 默认0；0：有效；1：失效")
    private Integer ownerStatus;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    @Schema(description = "创建人id")
    private String createId;

    /**
     * 状态,0 未删除 1已删除
     */
    @ApiModelProperty(value = "状态,0 未删除 1已删除")
    @Schema(description = "状态,0 未删除 1已删除")
    private Integer isFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(Integer ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id.equals(owner.id) && username.equals(owner.username) && gender.equals(owner.gender) && identity.equals(owner.identity) && phone.equals(owner.phone) && ownerStatus.equals(owner.ownerStatus) && createId.equals(owner.createId) && isFlag.equals(owner.isFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, gender, identity, phone, ownerStatus, createId, isFlag);
    }

}