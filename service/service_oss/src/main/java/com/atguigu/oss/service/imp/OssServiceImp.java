package com.atguigu.oss.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.util.UUID;

/**
 * @ClassName OssServiceImp
 * @Description TODO
 * @Author 24732
 * @Date 2022-04-02 10:40
 * @Return
 **/
@Service
public class OssServiceImp implements OssService {

    @Override
        public String upload(MultipartFile multipartFile) {

            //获取阿里云存储相关常量
            String endpoint = ConstantPropertiesUtils.END_POINT;
            String accessKeyId = ConstantPropertiesUtils.KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
            String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

            try {
                // 创建OSSClient实例。
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

                //上传文件流
                InputStream inputStream = multipartFile.getInputStream();

                //获取文件原始名
                String originalFilename = multipartFile.getOriginalFilename();

                //调用oss方法实现上传
                ossClient.putObject(bucketName, originalFilename, inputStream);

                //加上uuid使图片具备唯一性
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String fileName = uuid + originalFilename;

                //对图片根据时间分类管理
                String datePath = new DateTime().toString("yyyy/MM/dd");
                String fileName1 = datePath + "/" + fileName;
                //返回阿里云地址
                String url = "http://" + bucketName + "." + endpoint + "/" + fileName1;
                return url;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}
