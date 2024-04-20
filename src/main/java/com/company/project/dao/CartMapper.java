package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Cart;

import java.util.List;

public interface CartMapper extends Mapper<Cart> {

    List<Cart> list(Cart cart);

    void batchDelete(String[] array);

    Cart getByGoodsIdAndUserId(Cart cart);
}