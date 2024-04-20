package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SysMenu;
import com.company.project.service.SysMenuService;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/sys/menu")
@Api(tags = {"/sys/menu"}, description = "")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreatedAt(new Date());
        sysMenu.setIsDelete(false);
        sysMenuService.save(sysMenu);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(sysMenu);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        SysMenu sysMenu=new SysMenu();
        sysMenu.setId(id);
        sysMenu.setIsDelete(true);
        sysMenuService.update(sysMenu);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdatedAt(new Date());
        sysMenuService.update(sysMenu);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(sysMenu);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询单个详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new SysMenu());
        }else {
            SysMenu sysMenu = sysMenuService.findById(id);
            if (null == sysMenu){
                return ResultGenerator.genSuccessResult(new SysMenu());
            }else {
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(sysMenu);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) SysMenu sysMenu) {

        if (null == sysMenu){
            sysMenu = new SysMenu();
        }

        PageHelper.startPage(sysMenu.getPage() == null ? 0 : sysMenu.getPage(), sysMenu.getLimit() == null ? 10 : sysMenu.getLimit());
        sysMenu.setIsDelete(false);
        List<SysMenu> list = sysMenuService.findByModel(sysMenu);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
