package com.company.project.web;


import com.company.project.core.Result;
import com.company.project.service.GoodsService;
import com.company.project.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/index")
@Api(tags = {"/index"}, description = "")
public class IndexController {

    @Resource
    private OrderService orderService;

    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/selectIndexSaleDay", method = {RequestMethod.POST})
    public Result selectIndexSaleDay() {
        return orderService.selectIndexSaleDay();
    }

    @ApiOperation(value = "统计库存柱状图", notes = "统计库存柱状图")
    @RequestMapping(value = "/selectIndex", method = {RequestMethod.POST})
    public Result selectIndex() {
        return goodsService.selectIndex();
    }

}
