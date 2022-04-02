package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtils
 * @Description TODO
 * @Author 24732
 * @Date 2022-04-02 10:21
 * @Return
 **/
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyid;
        KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }
}
