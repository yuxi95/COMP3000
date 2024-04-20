package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.StockOutMapper;
import com.company.project.model.StockOut;
import com.company.project.service.StockOutService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StockOutServiceImpl extends AbstractService<StockOut> implements StockOutService {

    @Resource
    private StockOutMapper stockOutMapper;

    @Override
    public Result list(StockOut stockOut) {

        if (null == stockOut){
            stockOut = new StockOut();
        }

        PageHelper.startPage(stockOut.getPage() == null ? 0 : stockOut.getPage(), stockOut.getLimit() == null ? 10 : stockOut.getLimit());
        stockOut.setIsDelete(false);
        List<StockOut> list = stockOutMapper.list(stockOut);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
