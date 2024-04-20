package com.company.project.service.impl;

import com.company.project.dao.DrugTypeMapper;
import com.company.project.model.DrugType;
import com.company.project.service.DrugTypeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DrugTypeServiceImpl extends AbstractService<DrugType> implements DrugTypeService {

    @Resource
    private DrugTypeMapper tDrugTypeMapper;

}
