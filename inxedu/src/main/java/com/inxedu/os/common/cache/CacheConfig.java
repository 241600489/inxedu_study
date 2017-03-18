package com.inxedu.os.common.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
//@PropertySource(value = "classpath:/redis.properties")
public class CacheConfig extends CachingConfigurerSupport {
    private static long expirationTime=3600L;

    @Value("${REDIS_TOGGRE}")
    private String REDIS_TOGGRE;

    @Bean
    public KeyGenerator wiselyKeyGenerator(){

        if("true".equals(REDIS_TOGGRE)){
            return new KeyGenerator() {
                @Override
                public Object generate(Object target, Method method, Object... params) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append(method.getName());
                    for (Object obj : params) {
                        sb.append(obj.toString());
                    }
                    return sb.toString();
                }
            };
        }else {
            return null;
        }
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {

        if("true".equals(REDIS_TOGGRE)){
            RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
            // Number of seconds before expiration. Defaults to unlimited (0)
            cacheManager.setDefaultExpiration(expirationTime); //设置key-value超时时间
            //EHCacheUtil start
            EHCacheUtil.setRedisTemplate(redisTemplate);
            return cacheManager;
        }else {
            return null;
        }
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(@Autowired(required = false) @Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory factory) {
        if("true".equals(REDIS_TOGGRE)){
            StringRedisTemplate template = new StringRedisTemplate(factory);
            setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
            return template;
        }else {
            return null;
        }
    }
    private void setSerializer(StringRedisTemplate template) {
        if("true".equals(REDIS_TOGGRE)){
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);
            template.setValueSerializer(jackson2JsonRedisSerializer);
        }
    }
}