package com.atguigu.oss.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author 24732
 * @Date 2022-04-02 10:39
 * @Return
 **/
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController  {

    @Autowired
    private OssService ossService;

    //上传头像文件
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(@RequestBody MultipartFile multipartFile) {
        String upload = ossService.upload(multipartFile);
        //返回R对象
        return R.ok().message("文件上传成功").data("url", upload);
    }

}
