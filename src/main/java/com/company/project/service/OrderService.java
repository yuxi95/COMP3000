package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Order;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderService extends Service<Order> {

    Result add(Order order);

    Result list(String userId,Order order);

    Result detail(Long id);

    Result updateGoods(Order order);

    BigDecimal getMarketCount();

    BigDecimal getTodayCount(Date date);

    BigDecimal getMarketOrderCount();

    BigDecimal getTodayOrderCount(Date date);

    Result selectIndexSaleDay();
}
