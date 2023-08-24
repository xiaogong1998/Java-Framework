package com.gong.hitokoto.properties;

import com.alibaba.fastjson.annotation.JSONField;
import com.gong.hitokoto.enums.HitokotoTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "hitokoto.config", ignoreUnknownFields = true)
public class HitokotoProperties {

    private String apiUrl = "https://v1.hitokoto.cn";

    // 句子类型
    private HitokotoTypeEnum c = HitokotoTypeEnum.NetEaseCloud;

    // 返回编码
    private String encode = "json";

    // 字符集
    private String charset = "utf-8";

    // 返回句子的最小长度（包含）
    @JSONField(name = "min_length")
    private int minLength = 0;

    // 返回句子的最大长度（包含）
    @JSONField(name = "max_length")
    private int maxLength = 30;
}
