package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.ShippingAddressMapper;
import com.company.project.model.ShippingAddress;
import com.company.project.model.User;
import com.company.project.service.ShippingAddressService;
import com.company.project.core.AbstractService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ShippingAddressServiceImpl extends AbstractService<ShippingAddress> implements ShippingAddressService {

    @Resource
    private ShippingAddressMapper shippingAddressMapper;

    @Resource
    private UserService userService;

    @Override
    public Result list(String userId, ShippingAddress shippingAddress) {

        if (null == shippingAddress){
            shippingAddress = new ShippingAddress();
        }

        User user = userService.findByIdAndValidDelete(Long.valueOf(userId));
        if (null == user){
            return ResultGenerator.genFailResult("用户信息不存在[账号可能被停用或删除]");
        }

        if (0 == user.getRoleId()){
            shippingAddress.setCreatedBy(null);
        }else {
            shippingAddress.setCreatedBy(userId);
        }

        PageHelper.startPage(shippingAddress.getPage() == null ? 0 : shippingAddress.getPage(), shippingAddress.getLimit() == null ? 10 : shippingAddress.getLimit());
        shippingAddress.setIsDelete(false);
        List<ShippingAddress> list = shippingAddressMapper.list(shippingAddress);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
