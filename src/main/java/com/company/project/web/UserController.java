package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.utils.Logger;
import com.company.project.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(tags = {"/user"}, description = "")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public Result logout(@RequestParam Long userId) {
        Logger.info(this,"/user/logout 用户退出接口入参 : " + userId);
        return userService.logout(userId);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Result login(@RequestBody LoginVo vo, HttpServletRequest request) {
        Logger.info(this, "/user/login 用户登录接口入参 :" + vo);
        return userService.login(vo);
    }

    @RequestMapping(value = "/batchSendEmail", method = {RequestMethod.POST})
    public Result batchSendEmail(@RequestBody String ids) {
        return userService.batchSendEmail(ids);
    }

    @RequestMapping(value = "/captcha", method = {RequestMethod.POST})
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.captcha();
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/detailByToken", method = {RequestMethod.POST})
    public Result detailByToken() {
        if (super.getUserId() == null){
            return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"token不存在或token错误请重新登录");
        }else {
            return userService.detailByToken(Long.valueOf(super.getUserId()));
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam(value = "id", required = false) Long id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result updateUser(@RequestBody User user) {
        return  userService.updateUser(user);
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new User());
        }else {
            User user = userService.findById(id);
            if (null == user){
                return ResultGenerator.genSuccessResult(new User());
            }else {
                user.setRole(user.getRoleId());
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }

    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) User user) {
        return userService.list(user);
    }

    @RequestMapping(value = "/findAllColumns", method = {RequestMethod.POST})
    public Result findAllColumns() {
        return userService.findAllColumns();
    }

}
