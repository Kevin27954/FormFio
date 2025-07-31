package com.formkio.formfio.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
public class JedisConfiguration {

    @Bean
    public JedisPool jedisPool(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.database}") String database) {

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // TODO Make this have password and SSL cert ty
        return new JedisPool(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT);
//        return new JedisPool(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password, Integer.parseInt(database));
    }

}
