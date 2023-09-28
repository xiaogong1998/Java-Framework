package com.gong.aliyun.oss.serviceImpl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.gong.aliyun.oss.properties.AliOssProperties;
import com.gong.aliyun.oss.service.AliOssService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 阿里OSS实现类
 *
 * @author xiaogong
 * @since 2023/9/20 10:01
 */
@Service
public class AliOssServiceImpl implements AliOssService {

    @Resource
    private AliOssProperties properties;

    @Resource
    private OSSClient ossClient;


}
