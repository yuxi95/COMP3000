package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.StockInMapper;
import com.company.project.model.StockIn;
import com.company.project.service.StockInService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StockInServiceImpl extends AbstractService<StockIn> implements StockInService {

    @Resource
    private StockInMapper stockInMapper;

    @Override
    public Result list(StockIn stockIn) {

        if (null == stockIn){
            stockIn = new StockIn();
        }

        PageHelper.startPage(stockIn.getPage() == null ? 0 : stockIn.getPage(), stockIn.getLimit() == null ? 10 : stockIn.getLimit());
        stockIn.setIsDelete(false);
        List<StockIn> list = stockInMapper.list(stockIn);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
