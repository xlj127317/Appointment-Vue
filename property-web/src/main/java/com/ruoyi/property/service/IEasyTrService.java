package com.ruoyi.property.service;

public interface IEasyTrService {
    String userIdToOwnerId(String userId);
    String userIdToOwnerId(String userId, int lockType);
    String mustUserIdToOwnerId(String userId) throws Exception;
}
