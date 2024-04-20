package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.WantTo;
import com.company.project.service.WantToService;
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
@RequestMapping("/want/to")
@Api(tags = {"/want/to"}, description = "")
public class WantToController extends BaseController{
    @Resource
    private WantToService wantToService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody WantTo wantTo) {
        wantTo.setCreatedAt(new Date());
        wantTo.setIsDelete(false);
        wantTo.setCreatedBy(super.getUserId());
        wantToService.save(wantTo);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(wantTo);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        WantTo wantTo=new WantTo();
        wantTo.setId(id);
        wantTo.setIsDelete(true);
        wantToService.update(wantTo);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody WantTo wantTo) {
        wantTo.setUpdatedAt(new Date());
        wantToService.update(wantTo);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(wantTo);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new WantTo());
        }else {
            WantTo wantTo = wantToService.findById(id);
            if (null == wantTo){
                return ResultGenerator.genSuccessResult(new WantTo());
            }else {
                return ResultGenerator.genSuccessResult(wantTo);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) WantTo wantTo) {

        if (null == wantTo){
            wantTo = new WantTo();
        }

        PageHelper.startPage(wantTo.getPage() == null ? 0 : wantTo.getPage(), wantTo.getLimit() == null ? 10 : wantTo.getLimit());
        wantTo.setIsDelete(false);
        List<WantTo> list = wantToService.findByModel(wantTo);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
