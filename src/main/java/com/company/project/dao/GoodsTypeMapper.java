package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.GoodsType;

import java.util.List;

public interface GoodsTypeMapper extends Mapper<GoodsType> {

    List<GoodsType> list(GoodsType goodsType);

    String getNameById(Integer goodsType);

    List<GoodsType> findRandByModal(GoodsType goodsType);
}
