package com.formkio.formfio.repository.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfiguration {

    @Bean
    public JedisPool jedisPool(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.database}") String database,
            @Value("${spring.redis.name}") String name) {

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // TODO Make this have password and SSL cert ty
        return new JedisPool(host, port, name, password);
//        return new JedisPool(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password, Integer.parseInt(database));
    }

}
