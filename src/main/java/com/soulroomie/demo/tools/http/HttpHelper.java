package com.soulroomie.demo.tools.http;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.UUID;


public class HttpHelper {
     private static final String accessKey = "zMMI5Urij5GethphHP-8w2spJhP-HGI_1yxWEZWl";
     private static final String secretKey = "meMla437brxhQtEdKJGdFqGopbxoK0CqGTZ97Fyj";
     private static final String bucketName = "clnsxpicbed";
     private static final String DOMAIN = "http://blog.img.gottafindyou.cn/";


    public String  upload(byte[] fileBytes) throws QiniuException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        String imageName = UUID.randomUUID().toString();
        imageName = imageName + ".jpg";
        Response r = uploadManager.put(fileBytes, imageName, token);
        if (r.isOK()) {
            Ret ret = r.jsonToObject(Ret.class);
            return DOMAIN + ret.key;
        }else {
            return "http://blog.img.gottafindyou.cn/icon_256x256.png";
        }
    }




}
class Ret {
    public long fsize;
    public String key;
    public String hash;
    public int width;
    public int height;
}