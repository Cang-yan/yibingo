package com.yibingo.race.storage.cloud;

import cn.hutool.http.HttpUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @description: Oss基础客户端
 * @author: smalljop
 * @create: 2018-10-18 13:57
 **/
public abstract class OssStorageService {

    /**
     * oss配置
     */
    public OssStorageConfig config;


    /**
     * 文件上传
     *
     * @param inputStream 文件 上传路径
     * @param path
     * @return
     */
    public abstract String upload(InputStream inputStream, String path);


    /**
     * 文件上传
     *
     * @param data 文件 上传路径
     * @param path
     * @return
     */
    public abstract String upload(byte[] data, String path);


    public abstract String upload(MultipartFile multfile, String path);

    /**
     * 下载文件
     *
     * @param url 文件地址
     * @return
     */
    public InputStream download(String url) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        HttpUtil.download(url, output, false);
        return new ByteArrayInputStream(output.toByteArray());
    }

    /**
     * 删除文件
     *
     * @param path 相对于存储系统的路径
     * @return
     */
    public abstract void delete(String path);
}
