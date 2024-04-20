package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ShippingAddress;
import com.company.project.service.ShippingAddressService;
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
@RequestMapping("/shipping/address")
@Api(tags = {"/shipping/address"}, description = "")
public class ShippingAddressController extends BaseController{
    @Resource
    private ShippingAddressService shippingAddressService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody ShippingAddress shippingAddress) {
        shippingAddress.setCreatedAt(new Date());
        shippingAddress.setIsDelete(false);
        shippingAddress.setCreatedBy(super.getUserId());
        shippingAddressService.save(shippingAddress);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(shippingAddress);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        ShippingAddress shippingAddress=new ShippingAddress();
        shippingAddress.setId(id);
        shippingAddress.setIsDelete(true);
        shippingAddressService.update(shippingAddress);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody ShippingAddress shippingAddress) {
        shippingAddress.setUpdatedAt(new Date());
        shippingAddressService.update(shippingAddress);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(shippingAddress);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new ShippingAddress());
        }else {
            ShippingAddress shippingAddress = shippingAddressService.findById(id);
            if (null == shippingAddress){
                return ResultGenerator.genSuccessResult(new ShippingAddress());
            }else {
                return ResultGenerator.genSuccessResult(shippingAddress);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) ShippingAddress shippingAddress) {
        return shippingAddressService.list(super.getUserId(),shippingAddress);
    }
}
