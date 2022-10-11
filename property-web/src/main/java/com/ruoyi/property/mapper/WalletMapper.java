package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Wallet;

public interface WalletMapper {
    void createIfNotExists(String ownerId);
    Wallet getForUpdate(String ownerId);
}
