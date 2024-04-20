package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.GoodsType;

public interface GoodsTypeService extends Service<GoodsType> {

    Result list(GoodsType goodsType);

    String getNameById(Integer goodsType);

    Result detail(Long id);

    Result findRandByModal(GoodsType goodsType);
}
