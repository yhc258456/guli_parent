package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

@Service
public class OssServiceimpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String url = null;

        String keyId = ConstantPropertiesUtils.KEY_ID;
        String keysecret = ConstantPropertiesUtils.KEY_SECRET;
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        OSS client = new OSSClientBuilder().build(endpoint, keyId, keysecret);

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();


            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            int month = instance.get(Calendar.MONTH) + 1;
            int day = instance.get(Calendar.DAY_OF_MONTH);
            String uuid = UUID.randomUUID().toString().replace("-", "");

            client.putObject(bucketName, year + "/" + month + "/" + day + "/" + uuid + originalFilename, inputStream);

            // eg: https://edu-online-yhc.oss-cn-chengdu.aliyuncs.com/2020/08/20/0a09986a8e2f4c3c81d42f47047202b2file.png
            url = "https://" + bucketName + "." + endpoint + "/" + year + "/" + month + "/" + day + "/" + uuid + originalFilename;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }
        return url;
    }
}
