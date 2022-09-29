package com.ruoyi.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ReceivableCreateInput {
    private String contractId;
    private String paymentTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    private String paymentName;
    private BigDecimal receivableMoney;
    private String paymentContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiresDate;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public BigDecimal getReceivableMoney()
    {
        return receivableMoney;
    }

    public void setReceivableMoney(BigDecimal receivableMoney)
    {
        this.receivableMoney = receivableMoney;
    }

    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setPaymentExpires(Date expiresDate) {
        this.expiresDate = expiresDate;
    }
}
