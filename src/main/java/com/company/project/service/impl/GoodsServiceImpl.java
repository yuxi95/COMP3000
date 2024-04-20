package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.GoodsMapper;
import com.company.project.model.Goods;
import com.company.project.model.User;
import com.company.project.service.*;
import com.company.project.vo.DateIndexVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class GoodsServiceImpl extends AbstractService<Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private UserService userService;

    @Resource
    private GoodsTypeService goodsTypeService;

    @Resource
    private OrderDetailsService orderDetailsService;

    @Override
    public int findGoodsCount(Long goodsId) {
        return goodsMapper.findGoodsCount(goodsId);
    }

    @Override
    public List<Goods> selectByGoodsTypeId(Long goodsTypeId) {
        return goodsMapper.selectByGoodsTypeId(goodsTypeId);
    }

    @Override
    public Result detail(Long id, String userId) {
        Goods goods = goodsMapper.detail(id);
        if (null != goods){
            goods.setGoodsTypeName(goodsTypeService.getNameById(goods.getGoodsType()));
            if (null != goods.getCreatedBy()){
                List<User> userList = userService.findUserById(Long.valueOf(goods.getCreatedBy()));
                goods.setUserLikeDtoList(userList);
            }
            Random r = new Random(1);
            int number = r.nextInt(10);
            //增加曝光数
            goods.setExposureNumber(null == goods.getExposureNumber() ? 1 : goods.getExposureNumber() + number);
            //增加热度
            goods.setHeat(null == goods.getHeat() ? 1 : goods.getHeat() + 1);
            update(goods);
        }

        return ResultGenerator.genSuccessResult(goods);
    }

    @Override
    public Result list(Goods goods) {
        if (goods == null){
            goods = new Goods();
        }

        PageHelper.startPage(goods.getPage() == null ? 0 : goods.getPage(), goods.getLimit() == null ? 10 : goods.getLimit());
        goods.setIsDelete(false);
        List<Goods> list = goodsMapper.list(goods);
        if (list.size() > 0){
            for (Goods d:list) {
                d.setSalesVolume(orderDetailsService.getCountByGoodsId(d.getId()));
                d.setGoodsTypeName(goodsTypeService.getNameById(d.getGoodsType()));
                if (null != d.getCreatedBy()){
                    List<User> userList = userService.findUserById(Long.valueOf(d.getCreatedBy()));
                    d.setUserLikeDtoList(userList);
                }
            }
            list.sort(new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    Integer i1 = o1.getSalesVolume();
                    Integer i2 = o2.getSalesVolume();
                    return i1.compareTo(i2);
                }
            });
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result add(Goods goods) {
        goods.setCreatedAt(new Date());
        goods.setIsDelete(false);
        save(goods);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goods);
        return result;
    }

    @Override
    public Result delete(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setIsDelete(true);
        update(goods);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result updateGoods(Goods goods) {
        goods.setUpdatedAt(new Date());
        update(goods);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goods);
        return result;
    }

    @Override
    public Result findByNumber(Goods goods) {
        PageHelper.startPage(goods.getPage(), goods.getLimit());
        goods.setIsDelete(false);
        List<Goods> list = goodsMapper.list(goods);
        Collections.shuffle(list);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result findByAllIds(Goods goods) {
        String[] array = goods.getIds().split(",");
        List<Goods> list = goodsMapper.findByAllIds(array);
        return ResultGenerator.genSuccessResult(list);
    }

    @Override
    public Result selectIndex() {
        DateIndexVo dateIndexVo = new DateIndexVo();

        //科目集合
        List<String> subjectList = new ArrayList<>();

        //积分集合
        List<BigDecimal> scoreList = new ArrayList<>();

        List<Goods> list = goodsMapper.selectAllByIsDelete();
        if (list != null && list.size() > 0){
            for (Goods d:list) {
                subjectList.add(d.getGoodsName());
                scoreList.add(d.getRepertory());
            }
        }

        dateIndexVo.setDateList(subjectList);
        dateIndexVo.setNumberList(scoreList);

        return ResultGenerator.genSuccessResult(dateIndexVo);
    }

    @Override
    public BigDecimal getGoodsCount() {
        return goodsMapper.getGoodsCount();
    }

    @Override
    public Result findAllByModal(Goods goods) {
        PageHelper.startPage(0, 0);
        goods.setIsDelete(false);
        List<Goods> list = goodsMapper.findAllByModal(goods);
        if (list.size() > 0){
            for (Goods d:list) {
                d.setSalesVolume(orderDetailsService.getCountByGoodsId(d.getId()));
                d.setGoodsTypeName(goodsTypeService.getNameById(d.getGoodsType()));
                if (null != d.getCreatedBy()){
                    List<User> userList = userService.findUserById(Long.valueOf(d.getCreatedBy()));
                    d.setUserLikeDtoList(userList);
                }
            }
            list.sort(new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    Integer i1 = o1.getSalesVolume();
                    Integer i2 = o2.getSalesVolume();
                    //i2.compareTo(i1)倒叙
                    //i1.compareTo(i2)正序
                    return i2.compareTo(i1);
                }
            });
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
