package com.gong.kuaidi100.serviceImpl;

import com.gong.core.exception.ServiceException;
import com.gong.kuaidi100.properties.Kuaidi100Properties;
import com.gong.kuaidi100.service.Kuaidi100Service;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 快递100
 *
 * @author xiaogong
 * @since 2023/8/22 16:52
 */
@Slf4j
@Service
public class Kuaidi100ServiceImpl implements Kuaidi100Service {

    @Resource
    private Kuaidi100Properties properties;

    @Override
    public String queryTrack(String com, String num, String phone) {
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(com);
        queryTrackParam.setNum(num);
        if (StringUtils.isNotBlank(phone)) {
            queryTrackParam.setPhone(phone);
        }
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(properties.getCustomer());
        queryTrackReq.setSign(SignUtils.querySign(param, properties.getKey(), properties.getCustomer()));

        IBaseClient baseClient = new QueryTrack();

        try {
            HttpResult execute = baseClient.execute(queryTrackReq);
            if (200 == execute.getStatus()) {
                return execute.getBody();
            }
        } catch (Exception e) {
            log.error("查询失败，失败原因：{}", e.getMessage());
            throw new ServiceException("查询失败，失败原因：" + e.getMessage());
        }
        throw new ServiceException("查询失败!");
    }

    @Override
    public String queryTrack(String com, String num) {
        return this.queryTrack(com, num, null);
    }
}
