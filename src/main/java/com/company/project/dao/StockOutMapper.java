package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.StockOut;

import java.util.List;

public interface StockOutMapper extends Mapper<StockOut> {

    List<StockOut> list(StockOut stockOut);

}