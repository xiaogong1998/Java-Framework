package com.gong.wechat.subscription.serviceImpl;

import com.gong.wechat.subscription.model.MessageTemplate;
import com.gong.wechat.subscription.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/24 15:58
 */
@SpringBootTest
class SubscriptionServiceImplTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Test
    void send() {
        Map<String, MessageTemplate> map = new HashMap<>();
        map.put("name",new MessageTemplate("你好"));
        subscriptionService.sendText(Arrays.asList("oLKef6RzIWyjO2lskVn2QUCA7rcs","oLKef6a0zQmKEhpeNWvktb2_4yvE"),"7pjk0e0RPhpN5K-TJhe3h-DFbmU60EG-OjKxQZ8Zwvg",map);
    }
}