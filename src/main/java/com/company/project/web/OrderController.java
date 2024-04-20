package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Order;
import com.company.project.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Api(tags = {"/order"}, description = "")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Order order) {
        order.setCreatedBy(super.getUserId());
        return orderService.add(order);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Order order=new Order();
        order.setId(id);
        order.setIsDelete(true);
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/updateGoods", method = {RequestMethod.POST})
    public Result updateGoods(@RequestBody Order order) {
        return orderService.updateGoods(order);
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return orderService.detail(id);
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Order order) {
        return orderService.list(super.getUserId(),order);
    }
}
