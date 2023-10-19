package com.gong.redisson.service;

import com.gong.redisson.properties.RedissonProperties;
import org.redisson.config.Config;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/19 16:56
 */
public interface RedissonConfigStrategy {

    Config createRedissonConfig(RedissonProperties redissonProperties);
}
