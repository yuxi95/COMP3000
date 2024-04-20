package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DrugType;
import com.company.project.service.DrugTypeService;
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
@RequestMapping("/drug/type")
@Api(tags = {"/drug/type"}, description = "")
public class DrugTypeController extends BaseController{
    @Resource
    private DrugTypeService drugTypeService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody DrugType drugType) {
        drugType.setCreatedAt(new Date());
        drugType.setIsDelete(false);
        drugType.setCreatedBy(super.getUserId());
        drugTypeService.save(drugType);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(drugType);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        DrugType drugType=new DrugType();
        drugType.setId(id);
        drugType.setIsDelete(true);
        drugTypeService.update(drugType);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody DrugType drugType) {
        drugType.setUpdatedAt(new Date());
        drugTypeService.update(drugType);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(drugType);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new DrugType());
        }else {
            DrugType drugType = drugTypeService.findById(id);
            if (null == drugType){
                return ResultGenerator.genSuccessResult(new DrugType());
            }else {
                return ResultGenerator.genSuccessResult(drugType);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) DrugType drugType) {

        if (null == drugType){
            drugType = new DrugType();
        }

        PageHelper.startPage(drugType.getPage() == null ? 0 : drugType.getPage(), drugType.getLimit() == null ? 10 : drugType.getLimit());
        drugType.setIsDelete(false);
        List<DrugType> list = drugTypeService.findByModel(drugType);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
