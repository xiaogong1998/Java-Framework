package com.gong.wechat.subscription.enums;

import com.gong.core.annotation.BaseEnum;
import org.apache.commons.lang3.ObjectUtils;

public enum MessageErrEnum implements BaseEnum<Long> {

    TheSystemIsBusy(-1, "系统繁忙"),
    RequestSucceeded(0, "请求成功"),
    VerificationFailed(4001, "验证失败"),
    InvalidCredentialType(4002, "不合法的凭证类型"),
    InvalidOpenID(4003, "不合法的OpenID"),
    InvalidMediaFileType(4004, "不合法的媒体文件类型"),
    InvalidFileType(4005, "不合法的文件类型"),
    InvalidFileSize(4006, "不合法的文件大小"),
    InvalidMediaFileId(4007, "不合法的媒体文件id"),
    InvalidMessageType(4008, "不合法的消息类型"),
    InvalidImageFileSize(40009, "不合法的图片文件大小"),
    InvalidVoiceFileSize(40010, "不合法的语音文件大小"),
    InvalidVideoFileSize(40011, "不合法的视频文件大小"),
    InvalidThumbnailFileSize(40012, "不合法的缩略图文件大小"),
    InvalidAPPID(40013, "不合法的APPID"),
    MissingAccessTokenParameter(41001, "缺少access_token参数"),
    MissingAppidParameter(41002, "缺少appid参数"),
    MissingRefreshTokenParameter(41003, "缺少refresh_token参数"),
    MissingSecretParameter(41004, "缺少secret参数"),
    MissingMultimediaFileData(41005, "缺少多媒体文件数据"),
    AccessTokenTimeout(41006, "access_token超时"),
    GETRequestRequired(42001, "需要GET请求"),
    POSTRequestRequired(43002, "需要POST请求"),
    HTTPSRequestRequired(43003, "需要HTTPS请求"),
    UserNotSubscribed(43004, "用户未订阅"),
    MultimediaFileIsEmpty(44001, "多媒体文件为空"),
    POSTPacketIsEmpty(44002, "POST的数据包为空"),
    TheTextMessageContentIsEmpty(44003, "图文消息内容为空"),
    TheMultimediaFileSizeExceedsTheLimit(45001, "多媒体文件大小超过限制"),
    MessageContentExceedsLimit(45002, "消息内容超过限制"),
    TitleFieldExceedsLimit(45003, "标题字段超过限制"),
    DescriptionFieldExceedsLimit(45004, "描述字段超过限制"),
    LinkFieldExceedsLimit(45005, "链接字段超过限制"),
    ImageLinkFieldExceedsLimit(45006, "图片链接字段超过限制"),
    VoicePlaybackTimeExceedsTheLimit(45007, "语音播放时间超过限制"),
    TextMessageExceedsTheLimit(45008, "图文消息超过限制"),
    TheInterfaceCallExceedsTheLimit(45009, "接口调用超过限制"),
    NoMediaData(46001, "不存在媒体数据"),
    ErrorParsingJSONXMLContent(47001, "解析JSON/XML内容错误");

    private final Long code;

    private final String msg;

    MessageErrEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    @Override
    public Long getValue(){
        return this.code;
    }

    public static MessageErrEnum findByValue(Long code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (MessageErrEnum enumValue : values()) {
            if (enumValue.code().equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}
