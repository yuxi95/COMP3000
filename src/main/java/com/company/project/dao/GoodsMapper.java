package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsMapper extends Mapper<Goods> {

    int findGoodsCount(Long goodsId);

    List<Goods> selectByGoodsTypeId(Long goodsTypeId);

    Goods detail(Long id);

    List<Goods> list(Goods goods);

    List<Goods> findByAllIds(String[] array);

    List<Goods> findAllByModal(Goods goods);

    Goods findGoodsById(Long id);

    List<Goods> selectAllGoods();

    List<Goods> randList();

    BigDecimal getGoodsCount();

    List<Goods> selectAllByIsDelete();
}
