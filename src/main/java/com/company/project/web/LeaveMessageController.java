package com.company.project.web;
import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.LeaveMessage;
import com.company.project.service.LeaveMessageService;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/leave/message")
@Api(tags = {"/leave/message"}, description = "")
public class LeaveMessageController extends BaseController {
    @Resource
    private LeaveMessageService leaveMessageService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody LeaveMessage leaveMessage) {
        leaveMessage.setCreatedAt(new Date());
        leaveMessage.setIsDelete(false);
        leaveMessage.setCreatedBy(super.getUserId());
        leaveMessageService.save(leaveMessage);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(leaveMessage);
        return result;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        LeaveMessage leaveMessage=new LeaveMessage();
        leaveMessage.setId(id);
        leaveMessage.setIsDelete(true);
        leaveMessageService.update(leaveMessage);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody LeaveMessage leaveMessage) {
        leaveMessage.setUpdatedAt(new Date());
        leaveMessageService.update(leaveMessage);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(leaveMessage);
        return result;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询单个详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new LeaveMessage());
        }else {
            LeaveMessage leaveMessage = leaveMessageService.findById(id);
            if (null == leaveMessage){
                return ResultGenerator.genSuccessResult(new LeaveMessage());
            }else {
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(leaveMessage);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) LeaveMessage leaveMessage) {

        if (null == leaveMessage){
            leaveMessage = new LeaveMessage();
        }

        PageHelper.startPage(leaveMessage.getPage() == null ? 0 : leaveMessage.getPage(), leaveMessage.getLimit() == null ? 10 : leaveMessage.getLimit());
        leaveMessage.setIsDelete(false);
        List<LeaveMessage> list = leaveMessageService.findByModel(leaveMessage);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
