package com.yibingo.race.storage.cloud;


import cn.hutool.core.util.StrUtil;

import com.yibingo.race.common.utils.ServletUtils;
import com.yibingo.race.common.utils.SpringContextUtils;
import com.yibingo.race.storage.enums.OssTypeEnum;
import org.springframework.boot.system.ApplicationHome;

/**
 * 文件上传Factory
 *
 * @author smalljop
 */
public final class OssStorageFactory {
    private static OssStorageConfig config;

    static {
        config = SpringContextUtils.getBean(OssStorageConfig.class);
    }


    public static OssStorageService build() {
        if (config.getOssType() == OssTypeEnum.QINIU) {
            return new QiniuCloudStorageService(config);
        } else if (config.getOssType() == OssTypeEnum.ALIYUN) {
            return new AliyunOssStorageService(config);
        } else if (config.getOssType() == OssTypeEnum.UPYUN) {
            return new UpyunStorageService(config);
        } else if (config.getOssType() == OssTypeEnum.LOCAL) {
            // 本地存储默认配置
            if (StrUtil.isBlank(config.getUploadFolder())) {
                ApplicationHome ah = new ApplicationHome(OssStorageFactory.class);
                config.setUploadFolder(ah.getDir().getAbsolutePath());
            }
            if (StrUtil.isBlank(config.getDomain())) {
                String domain = ServletUtils.getDomain(ServletUtils.getRequest());
                config.setDomain(domain + "/profile/");
            }

            return new localStorageService(config);
        } else if (config.getOssType() == OssTypeEnum.MINIO) {
            return new MIniOStorageService(config);
        }else if (config.getOssType() == OssTypeEnum.QCLOD){
            return new QcloudStorageService(config);
        }
        return null;
    }

}
