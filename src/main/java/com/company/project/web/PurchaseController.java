package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Purchase;
import com.company.project.service.PurchaseService;
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
@RequestMapping("/purchase")
@Api(tags = {"/purchase"}, description = "")
public class PurchaseController extends BaseController{
    @Resource
    private PurchaseService purchaseService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Purchase purchase) {
        purchase.setCreatedAt(new Date());
        purchase.setIsDelete(false);
        purchase.setCreatedBy(super.getUserId());
        purchaseService.save(purchase);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(purchase);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Purchase purchase=new Purchase();
        purchase.setId(id);
        purchase.setIsDelete(true);
        purchaseService.update(purchase);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Purchase purchase) {
        purchase.setUpdatedAt(new Date());
        purchaseService.update(purchase);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(purchase);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Purchase());
        }else {
            Purchase purchase = purchaseService.findById(id);
            if (null == purchase){
                return ResultGenerator.genSuccessResult(new Purchase());
            }else {
                return ResultGenerator.genSuccessResult(purchase);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Purchase purchase) {

        if (null == purchase){
            purchase = new Purchase();
        }

        PageHelper.startPage(purchase.getPage() == null ? 0 : purchase.getPage(), purchase.getLimit() == null ? 10 : purchase.getLimit());
        purchase.setIsDelete(false);
        List<Purchase> list = purchaseService.findByModel(purchase);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
