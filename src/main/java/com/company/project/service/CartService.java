package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Cart;

public interface CartService extends Service<Cart> {

    Result list(String userId,Cart cart);

    Result batchDelete(Cart cart);

    Result add(Cart cart);
}
