package com.ruoyi.property.service;

import com.ruoyi.property.domain.Wallet;

import java.util.Map;

public interface IWalletService {
    void createIfNotExists(String ownerId);
    Wallet getForUpdate(String ownerId);
    void updateAmount(Map params);
}
