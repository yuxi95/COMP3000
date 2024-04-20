package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.GoodsTypeMapper;
import com.company.project.model.GoodsType;
import com.company.project.service.GoodsService;
import com.company.project.service.GoodsTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class GoodsTypeServiceImpl extends AbstractService<GoodsType> implements GoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Resource
    private GoodsService goodsService;

    @Override
    public Result list(GoodsType goodsType) {

        if (goodsType == null){
            goodsType = new GoodsType();
        }
        PageHelper.startPage(goodsType.getPage() == null ? 0 : goodsType.getPage(), goodsType.getLimit() == null ? 10 : goodsType.getLimit());
        goodsType.setIsDelete(false);
        List<GoodsType> list = goodsTypeMapper.list(goodsType);
        for (GoodsType d:list) {
            d.setGoodsList(goodsService.selectByGoodsTypeId(d.getId()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public String getNameById(Integer goodsType) {
        return goodsTypeMapper.getNameById(goodsType);
    }

    @Override
    public Result detail(Long id) {
        GoodsType goodsType = findById(id);
        if (goodsType != null){
            goodsType.setGoodsList(goodsService.selectByGoodsTypeId(goodsType.getId()));
        }
        return ResultGenerator.genSuccessResult(goodsType);
    }

    @Override
    public Result findRandByModal(GoodsType goodsType) {
        PageHelper.startPage(0, 5);
        goodsType.setIsDelete(false);
        List<GoodsType> list = goodsTypeMapper.findRandByModal(goodsType);
        for (GoodsType d:list) {
            d.setGoodsList(goodsService.selectByGoodsTypeId(d.getId()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
