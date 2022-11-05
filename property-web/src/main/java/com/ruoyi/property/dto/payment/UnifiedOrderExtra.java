package com.ruoyi.property.dto.payment;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnifiedOrderExtra {
    private String topic;
    private Object data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Object getData() {
        return data;
    }

    public <T> T toObject() {
        return (T)data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
