package com.gong.kuaidi100.service;

import com.kuaidi100.sdk.request.QueryTrackParam;

/**
 * TODO 快递100
 *
 * @author xiaogong
 * @since 2023/8/22 16:51
 */
public interface Kuaidi100Service {

    /**
     * 实时快递查询接口
     *
     * @param com   快递公司编码
     * @param num   查询的快递单号， 单号的最小长度6个字符，最大长度32个字符
     * @param phone 收、寄件人的电话号码（手机和固定电话均可，只能填写一个，顺丰速运、顺丰快运和丰网速运必填，其他快递公司选填。如座机号码有分机号，分机号无需传入。）
     * @return 快递信息
     */
    String queryTrack(String com, String num, String phone);

    /**
     * 实时快递查询接口
     *
     * @param com 快递公司编码
     * @param num 查询的快递单号， 单号的最小长度6个字符，最大长度32个字符
     * @return 快递信息
     */
    String queryTrack(String com, String num);


}
