package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.GoodsType;
import com.company.project.service.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/goods/type")
@Api(tags = {"/goods/type"}, description = "")
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody GoodsType goodsType) {
        goodsType.setCreatedAt(new Date());
        goodsType.setIsDelete(false);
        goodsTypeService.save(goodsType);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goodsType);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        GoodsType goodsType=new GoodsType();
        goodsType.setId(id);
        goodsType.setIsDelete(true);
        goodsTypeService.update(goodsType);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody GoodsType goodsType) {
        goodsType.setUpdatedAt(new Date());
        goodsTypeService.update(goodsType);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goodsType);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return goodsTypeService.detail(id);
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) GoodsType goodsType) {
        return goodsTypeService.list(goodsType);
    }

    @RequestMapping(value = "/findRandByModal", method = {RequestMethod.POST})
    public Result findRandByModal(@RequestBody(required =false) GoodsType goodsType) {
        return goodsTypeService.findRandByModal(goodsType);
    }
}
