package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.StockIn;
import com.company.project.core.Service;

public interface StockInService extends Service<StockIn> {

    Result list(StockIn stockIn);
}
