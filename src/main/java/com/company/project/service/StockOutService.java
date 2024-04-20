package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.StockOut;
import com.company.project.core.Service;

public interface StockOutService extends Service<StockOut> {

    Result list(StockOut stockOut);
}
