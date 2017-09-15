package com.monitor.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by yhe.abcft on 2017/9/15.
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24 * 60 * 60)
public class SessionConfig {
}
