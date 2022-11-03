package com.ruoyi.property.service.impl;

import com.ruoyi.property.mapper.EasyTrMapper;
import com.ruoyi.property.service.IEasyTrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class EasyTrServiceImpl implements IEasyTrService {
    @Resource
    private EasyTrMapper mapper;

    @Override
    public String userIdToOwnerId(String userId) {
        return userIdToOwnerId(userId, 0);
    }

    @Override
    public String userIdToOwnerId(String userId, int lockType) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("lockMode", lockType);
        return mapper.userIdToOwnerId(params);
    }

    @Override
    public String mustUserIdToOwnerId(String userId) throws Exception {
        String ownerId = userIdToOwnerId(userId);
        if (ownerId == null) {
            throw new Exception("指定用户未绑定业主关系");
        }
        return ownerId;
    }
}
