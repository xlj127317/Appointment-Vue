package com.ruoyi.property.service.impl;

import com.ruoyi.property.domain.Wallet;
import com.ruoyi.property.mapper.WalletMapper;
import com.ruoyi.property.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Map;

@Service
public class WalletService implements IWalletService {
    @Autowired
    private WalletMapper mapper;

    public void createIfNotExists(String ownerId)
    {
        mapper.createIfNotExists(ownerId);
    }

    public Wallet getForUpdate(String ownerId)
    {
        mapper.createIfNotExists(ownerId);
        return mapper.getForUpdate(ownerId);
    }

    @Override
    public void updateAmount(Map params)
    {

    }
}
