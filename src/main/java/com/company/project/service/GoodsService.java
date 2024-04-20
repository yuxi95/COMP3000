package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService extends Service<Goods> {

    int findGoodsCount(Long goodsId);

    List<Goods> selectByGoodsTypeId(Long id);

    Result detail(Long id, String userId);

    Result list(Goods goods);

    Result add(Goods goods);

    Result delete(Long id);

    Result updateGoods(Goods goods);

    Result findByNumber(Goods goods);

    Result findByAllIds(Goods goods);

    Result findAllByModal(Goods goods);

    BigDecimal getGoodsCount();

    Result selectIndex();
}
