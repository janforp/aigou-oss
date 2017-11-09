package com.aigou.oss.service;

import com.aigou.oss.api.AliOss;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Created by Summer on 16/3/1.
 */
@Service
public class UploadService {

    public String upload(MultipartFile file, String type) {
        String url = null;
        try {
            url =  AliOss.uploadMultipartFile("shaidan/"+UUID.randomUUID().toString(), file, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
