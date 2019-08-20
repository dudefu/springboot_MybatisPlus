package com.xinyi.xinfo.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration

@AutoConfigureAfter(RedisAutoConfiguration.class)

public class RedisCacheAutoConfiguration
{

    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        // 键的序列化方式

        template.setKeySerializer(new StringRedisSerializer());

        // 值的序列化方式

        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);

        return template;

    }

}
