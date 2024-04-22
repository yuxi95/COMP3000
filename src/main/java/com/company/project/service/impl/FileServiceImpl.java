package com.company.project.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import com.company.project.utils.Constants;
import com.company.project.utils.FilePathUtil;
import com.company.project.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    //项目路径
    private static String uploadDir = FilePathUtil.filePath();

    //判断系统
    private static String sysDir = FilePathUtil.OS_PREFIX;

    @Override
    public Result uploadSinglePhoto(HttpServletRequest request, MultipartFile file) {

        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            file.transferTo(new File(uploadDir + "/" + fileName));
            String path = new String();
            if (sysDir.contains("D")){
                // request.getScheme() 获取请求的协议名称
                // request.getServerName() 获取请求的域名
                // request.getServerPort() 获取请求的端口号
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(path);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    /**
     * 本地文件服务器(区分Linux和Windows和Mac操作系统)
     * @param request
     * @param file
     * @return
     */
    /*@Override
    public Result uploadSinglePhoto(HttpServletRequest request, MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            file.transferTo(new File(uploadDir + "/" + fileName));
            String path;
            if (sysDir.contains("D")){
                // request.getScheme() 获取请求的协议名称
                // request.getServerName() 获取请求的域名
                // request.getServerPort() 获取请求的端口号
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(path);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }*/


    @Override
    public Result uploadSingleName(HttpServletRequest request, MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadDir + "/" + fileName));
            String path;
            if (sysDir.contains("D")){
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(fileName);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    /**
     * 文件上传
     * @param
     * @return
     @Override
     public Result uploadSingle(HttpServletRequest request,MultipartFile file) {
     if (null == file) {
     return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
     }
     // 获取文件名
     String fileName = file.getOriginalFilename();
     // 获取文件的后缀名
     String suffixName = fileName.substring(fileName.lastIndexOf("."));
     // 文件上传后的路径
     String filePath = uploadDir;
     // 解决中文问题，liunx下中文路径，图片显示问题
     fileName = UUID.randomUUID() + suffixName;
     File dest = new File(filePath + fileName);
     // 检测是否存在目录
     if (!dest.getParentFile().exists()) {
     dest.getParentFile().mkdirs();
     }
     try {
     file.transferTo(dest);
     String path;
     //判断系统是否包含D，如果包含D的话，服务为Windows环境
     if (uploadDir.contains("D")){
     // request.getScheme() 获取请求的协议名称
     // request.getServerName() 获取请求的域名
     // request.getServerPort() 获取请求的端口号
     path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/"  + dest.getName();
     }else {
     //Linux环境
     path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + dest.getName();
     }
     return ResultGenerator.genSuccessResult(path);
     } catch (IllegalStateException e) {
     e.printStackTrace();
     } catch (IOException e) {
     e.printStackTrace();
     }
     return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
     }*/

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response) {

    }

    /*@Override
    public Result uploadSingleName(HttpServletRequest request, MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = uploadDir;
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String path;
            //判断系统是否包含D，如果包含D的话，服务为Windows环境
            if (uploadDir.contains("D")){
                // request.getScheme() 获取请求的协议名称
                // request.getServerName() 获取请求的域名
                // request.getServerPort() 获取请求的端口号
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/"  + dest.getName();
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + dest.getName();
            }
            return ResultGenerator.genSuccessResult(dest.getName());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }*/

}
