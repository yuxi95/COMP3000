package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.StockOut;
import com.company.project.service.StockOutService;
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
@RequestMapping("/stock/out")
@Api(tags = {"/stock/out"}, description = "")
public class StockOutController extends BaseController{
    @Resource
    private StockOutService stockOutService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody StockOut stockOut) {
        stockOut.setCreatedAt(new Date());
        stockOut.setIsDelete(false);
        stockOut.setCreatedBy(super.getUserId());
        stockOutService.save(stockOut);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(stockOut);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        StockOut stockOut=new StockOut();
        stockOut.setId(id);
        stockOut.setIsDelete(true);
        stockOutService.update(stockOut);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody StockOut stockOut) {
        stockOut.setUpdatedAt(new Date());
        stockOutService.update(stockOut);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(stockOut);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new StockOut());
        }else {
            StockOut stockOut = stockOutService.findById(id);
            if (null == stockOut){
                return ResultGenerator.genSuccessResult(new StockOut());
            }else {
                return ResultGenerator.genSuccessResult(stockOut);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) StockOut stockOut) {
        return stockOutService.list(stockOut);
    }
}
