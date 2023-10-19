package com.gong.redisson.manager;

import com.gong.redisson.properties.RedissonProperties;
import com.gong.redisson.service.RedissonConfigStrategy;
import com.gong.redisson.serviceImpl.StandaloneRedissonConfigStrategyImpl;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/19 16:51
 */
@Slf4j
public class RedissonManager {
    private Config config = new Config();

    private Redisson redisson = null;

    public RedissonManager() {
    }

    public RedissonManager(RedissonProperties redissonProperties) {
        // 装配开关
        Boolean enabled = redissonProperties.getEnabled();
        if (enabled) {
            try {
                config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
                redisson = (Redisson) Redisson.create(config);
            } catch (Exception e) {
                log.error("Redisson初始化错误", e);
            }
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        Config createConfig(RedissonProperties redissonProperties) {
            Preconditions.checkNotNull(redissonProperties);
            Preconditions.checkNotNull(redissonProperties.getAddress(), "redis地址未配置");
            RedissonConfigStrategy redissonConfigStrategy = new StandaloneRedissonConfigStrategyImpl();
            Preconditions.checkNotNull(redissonConfigStrategy, "连接方式创建异常");
            return redissonConfigStrategy.createRedissonConfig(redissonProperties);
        }
    }
}
