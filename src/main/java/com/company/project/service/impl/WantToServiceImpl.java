package com.company.project.service.impl;

import com.company.project.dao.WantToMapper;
import com.company.project.model.WantTo;
import com.company.project.service.WantToService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class WantToServiceImpl extends AbstractService<WantTo> implements WantToService {

    @Resource
    private WantToMapper tWantToMapper;

}
