package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.model.Goods;
import com.company.project.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
@Api(tags = {"/goods"}, description = "")
public class GoodsController extends BaseController {
    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Goods goods) {
        goods.setCreatedBy(super.getUserId());
        return goodsService.add(goods);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        return goodsService.delete(id);
    }

    @RequestMapping(value = "/updateGoods", method = {RequestMethod.POST})
    public Result updateGoods(@RequestBody Goods goods) {
        return goodsService.updateGoods(goods);
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id, @RequestParam(required = false) String userId) {
        return goodsService.detail(id,userId);
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Goods goods) {
        return goodsService.list(goods);
    }

    @RequestMapping(value = "/findByNumber", method = {RequestMethod.POST})
    public Result findByNumber(@RequestBody(required =false) Goods goods) {
        return goodsService.findByNumber(goods);
    }

    @RequestMapping(value = "/findByAllIds", method = {RequestMethod.POST})
    public Result findByAllIds(@RequestBody(required =false) Goods goods) {
        return goodsService.findByAllIds(goods);
    }

    @RequestMapping(value = "/findAllByModal", method = {RequestMethod.POST})
    public Result findAllByModal(@RequestBody(required =false) Goods goods) {
        return goodsService.findAllByModal(goods);
    }

}
