package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Cart;
import com.company.project.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/cart")
@Api(tags = {"/cart"}, description = "")
public class CartController extends BaseController {
    @Resource
    private CartService cartService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Cart cart) {
        cart.setCreatedAt(new Date());
        cart.setCreatedBy(super.getUserId());
        cart.setFlag(0);
        cart.setIsDelete(false);
        return cartService.add(cart);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Cart cart=new Cart();
        cart.setId(id);
        cart.setIsDelete(true);
        cartService.update(cart);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Cart cart) {
        cart.setUpdatedAt(new Date());
        cartService.update(cart);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(cart);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        Cart cart = cartService.findById(id);
        return ResultGenerator.genSuccessResult(cart);
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Cart cart) {
        return cartService.list(super.getUserId(),cart);
    }

    @RequestMapping(value = "/batchDelete", method = {RequestMethod.POST})
    public Result batchDelete(@RequestBody() Cart cart) {
        return cartService.batchDelete(cart);
    }

}
