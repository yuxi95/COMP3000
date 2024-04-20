package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Api(tags = {"/file"},description="")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/uploadSingle", method = {RequestMethod.POST})
    public Result uploadSingle(HttpServletRequest request,MultipartFile file){
        return fileService.uploadSinglePhoto(request,file);
    }

    @RequestMapping(value = "/export", method = {RequestMethod.GET})
    public void export(HttpServletRequest request, HttpServletResponse response) {
        fileService.export(request,response);
    }

}
