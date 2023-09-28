package com.gong.aliyun.oss.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.*;
import com.gong.aliyun.oss.properties.AliOssProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 阿里OSS配置
 *
 * @author xiaogong
 * @since 2023/9/20 9:20
 */
@Configuration
public class AliOssConfiguration {

    @Bean
    public OSSClient oss(AliOssProperties properties) {
        return new OSSClient(properties.getEndpoint(),
                new DefaultCredentialProvider(properties.getAccessKey(), properties.getSecretKey()),
                new ClientConfiguration());
    }

    /**
     * 创建存储空间
     * @param ossClient
     * @param bucketName
     */
    public void createBucket(OSSClient ossClient, String bucketName) {
        if (!ossClient.doesBucketExist(bucketName)) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            // 如果创建存储空间的同时需要指定存储类型、存储空间的读写权限、数据容灾类型, 请参考如下代码。
            // 此处以设置存储空间的存储类型为标准存储为例介绍。
            createBucketRequest.setStorageClass(StorageClass.Standard);
            // 数据容灾类型默认为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请设置为DataRedundancyType.ZRS。
            createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS);
            // 设置存储空间读写权限为公共读，默认为私有。
            createBucketRequest.setCannedACL(CannedAccessControlList.Private);
            // 设置地域为北京
            createBucketRequest.setLocationConstraint(LocationConstraint.OSS_CN_BEIJING);
            // 在支持资源组的地域创建Bucket时，您可以为Bucket配置资源组，填写资源组ID。如果不填写资源组ID，则创建的Bucket属于默认资源组。
            createBucketRequest.setResourceGroupId(null);
            ossClient.createBucket(bucketName);
        }
    }
}
