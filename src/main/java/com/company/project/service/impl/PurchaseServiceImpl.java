package com.company.project.service.impl;

import com.company.project.dao.PurchaseMapper;
import com.company.project.model.Purchase;
import com.company.project.service.PurchaseService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class PurchaseServiceImpl extends AbstractService<Purchase> implements PurchaseService {

    @Resource
    private PurchaseMapper tPurchaseMapper;

}
