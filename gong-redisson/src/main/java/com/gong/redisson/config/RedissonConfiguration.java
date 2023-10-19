package com.gong.redisson.config;

import com.gong.redisson.manager.RedissonManager;
import com.gong.redisson.properties.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/19 16:49
 */
@Slf4j
@Configuration
@ConditionalOnClass({RedissonProperties.class})
@EnableConfigurationProperties({RedissonProperties.class})
public class RedissonConfiguration {

    @Bean
    @ConditionalOnMissingBean({RedissonClient.class})
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        RedissonManager redissonManager = new RedissonManager(redissonProperties);
        log.info("RedissonManager初始化完成,连接地址:" + redissonProperties.getAddress());
        return redissonManager.getRedisson();
    }
}
