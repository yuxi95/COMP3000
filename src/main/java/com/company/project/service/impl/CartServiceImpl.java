package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CartMapper;
import com.company.project.model.Cart;
import com.company.project.model.User;
import com.company.project.service.CartService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl extends AbstractService<Cart> implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private UserService userService;

    @Override
    public Result list(String userId,Cart cart) {
        if (cart == null){
            cart = new Cart();
        }

        User user = userService.findByIdAndValidDelete(Long.valueOf(userId));
        if (user != null){
            if (0 == user.getRoleId()){
                cart.setCreatedBy(null);
            }else {
                cart.setCreatedBy(userId);
            }
        }

        PageHelper.startPage(cart.getPage() == null ? 0 : cart.getPage(), cart.getLimit() == null ? 10 : cart.getLimit());
        cart.setIsDelete(false);
        List<Cart> list = cartMapper.list(cart);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result add(Cart cart) {

        Cart newCart = cartMapper.getByGoodsIdAndUserId(cart);
        if (newCart != null){
            newCart.setCreatedBy(cart.getCreatedBy());
            newCart.setNumber(new BigDecimal(newCart.getNumber()).add(new BigDecimal(cart.getNumber())).intValue());
            update(newCart);
        }else {
            save(cart);
        }

        Result result= ResultGenerator.genSuccessResult();
        result.setData(cart);
        return result;
    }

    @Override
    public Result batchDelete(Cart cart) {
        String[] array = cart.getIds().split(",");
        for (int i = 0; i <array.length; i++){
            cart.setIsDelete(true);
            cart.setId(Long.valueOf(array[i]));
            update(cart);
        }
        return ResultGenerator.genSuccessResult();
    }

}
