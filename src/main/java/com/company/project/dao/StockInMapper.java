package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.StockIn;

import java.util.List;

public interface StockInMapper extends Mapper<StockIn> {

    List<StockIn> list(StockIn stockIn);

}