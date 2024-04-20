package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.StockIn;
import com.company.project.service.StockInService;
import com.company.project.common.BaseController;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/stock/in")
@Api(tags = {"/stock/in"}, description = "")
public class StockInController extends BaseController{
    @Resource
    private StockInService stockInService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody StockIn stockIn) {
        stockIn.setCreatedAt(new Date());
        stockIn.setIsDelete(false);
        stockIn.setCreatedBy(super.getUserId());
        stockInService.save(stockIn);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(stockIn);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        StockIn stockIn=new StockIn();
        stockIn.setId(id);
        stockIn.setIsDelete(true);
        stockInService.update(stockIn);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody StockIn stockIn) {
        stockIn.setUpdatedAt(new Date());
        stockInService.update(stockIn);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(stockIn);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new StockIn());
        }else {
            StockIn stockIn = stockInService.findById(id);
            if (null == stockIn){
                return ResultGenerator.genSuccessResult(new StockIn());
            }else {
                return ResultGenerator.genSuccessResult(stockIn);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) StockIn stockIn) {
        return stockInService.list(stockIn);
    }
}
