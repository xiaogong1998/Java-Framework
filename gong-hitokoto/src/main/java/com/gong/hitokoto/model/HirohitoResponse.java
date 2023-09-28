package com.gong.hitokoto.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HirohitoResponse {

    // 一言标识
    private Long id;

    // 一言正文。编码方式 unicode。使用 utf-8。
    private String hitokoto;

    // 类型。请参考第三节参数的表格HitokotoService
    private String type;

    // 一言的出处
    private String from;

    // 一言的作者
    @JSONField(name = "from_who")
    private String fromWho;

    // 添加者
    private String creator;

    // 添加者用户标识
    @JSONField(name = "creator_uid")
    private String creatorUid;

    // 审核员标识
    private String reviewer;

    // 一言唯一标识
    // 可以链接到 https://hitokoto.cn?uuid=[uuid] (opens new window)查看这个一言的完整信息
    private String uuid;

    // 提交方式
    @JSONField(name = "commit_from")
    private String commitFrom;

    // 添加时间
    @JSONField(name = "created_at")
    private String createdAt;

    // 句子长度
    private String length;
}
