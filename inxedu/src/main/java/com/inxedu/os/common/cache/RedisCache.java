package com.inxedu.os.common.cache;


import com.inxedu.os.common.util.SerializeUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.Map.Entry;


public class RedisCache implements ICache {

    // -1 - never expire
    private int expire = -1;
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String deleteCached(final byte[] sessionId) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.del(sessionId);
                return null;
            }
        });
        return null;
    }

    @Override
    public String updateCached(final byte[] key, final byte[] session,
                               final Long expireSec) {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                connection.set(key, session);
                if (expireSec != null) {
                    connection.expire(key, expireSec);
                } else {
                    connection.expire(key, expire);
                }
                return new String(key);
            }
        });

    }

    @Override
    public Object getCached(final byte[] sessionId) {
        return redisTemplate.execute((RedisConnection connection) -> {
                byte[] bs = connection.get(sessionId);
                return SerializeUtil.unserialize(bs);
        });
    }

    @Override
    public Set<byte[]> getKeys(final byte[] keys) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Set<byte[]> setByte = connection.keys(keys);
                if (setByte == null || setByte.size() < 1) {
                    return null;
                }
                Set set = new HashSet();
                for (byte[] key : setByte) {
                    byte[] bs = connection.get(key);
                    set.add(bs);
                }

                return set;
        });
    }

    @Override
    public Set getHashKeys(final byte[] key) {
        return (Set) redisTemplate.execute((RedisConnection connection) -> {
                Set<byte[]> hKeys = connection.hKeys(key);
                if (hKeys == null || hKeys.size() > 1) {
                    return null;
                }
                Set set = new HashSet();
                for (byte[] bs : hKeys) {
                    set.add(SerializeUtil.unserialize(bs));
                }
                return set;
        });
    }

    @Override
    public Boolean updateHashCached(final byte[] key, final byte[] mapkey,
                                    final byte[] value, final Long expireSec) {

        return redisTemplate.execute((RedisConnection connection) -> {
                Boolean hSet = connection.hSet(key, mapkey, value);
                if (expireSec != null) {
                    connection.expire(key, expireSec);
                } else {
                    connection.expire(key, expire);
                }
                return hSet;
        });
    }

    @Override
    public Object getHashCached(final byte[] key, final byte[] mapkey) {
        return redisTemplate.execute((RedisConnection connection) -> {
                byte[] hGet = connection.hGet(key, mapkey);
                return SerializeUtil.unserialize(hGet);
        });
    }

    @Override
    public Long deleteHashCached(final byte[] key, final byte[] mapkey) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Long hDel = connection.hDel(key, mapkey);
                return hDel;
        });
    }

    @Override
    public Long getHashSize(final byte[] key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Long len = connection.hLen(key);
                return len;
        });
    }

    @Override
    public Long getDBSize() {
        return redisTemplate.execute((RedisConnection connection) -> {
                Long len = connection.dbSize();
                return len;
        });
    }

    @Override
    public void clearDB() {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.flushDb();
                return null;
        });
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public List getHashValues(final byte[] key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                List<byte[]> hVals = connection.hVals(key);

                if (hVals == null || hVals.size() < 1) {
                    return null;
                }
                List list = new ArrayList();

                for (byte[] bs : hVals) {
                    list.add(SerializeUtil.unserialize(bs));
                }
                return list;
        });
    }

    public boolean zExists(final String key, final String value) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.zScore(redisTemplate.getStringSerializer()
                        .serialize(key), redisTemplate.getStringSerializer()
                        .serialize(value)) != null;
        });
    }

    public void zAdd(final String key, final String value, final double score) {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.zAdd(
                        redisTemplate.getStringSerializer().serialize(key),
                        score,
                        redisTemplate.getStringSerializer().serialize(value));
                return true;
        });
    }

    public void zAdd(final String key, final Map<String, Long> map) {
        redisTemplate.execute((RedisConnection connection) -> {
                for (Entry<String, Long> entry : map.entrySet()) {
                    connection.zAdd(redisTemplate.getStringSerializer()
                            .serialize(key), entry.getValue(), redisTemplate
                            .getStringSerializer().serialize(entry.getKey()));
                }
                return true;
        });
    }

    public long zRem(final String key, final String member) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.zRem(
                        redisTemplate.getStringSerializer().serialize(key),
                        redisTemplate.getStringSerializer().serialize(member));
        });
    }

    public Set<String> zPop(final String key, final int begin, final int end) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Set<String> jsons = new LinkedHashSet<String>();

                Set<byte[]> set = connection.zRange(redisTemplate
                        .getStringSerializer().serialize(key), begin, end);

                if (set != null) {

                    for (byte[] bytes : set) {

                        connection.zRem(redisTemplate.getStringSerializer()
                                .serialize(key), bytes);

                        jsons.add((String) redisTemplate.getStringSerializer()
                                .deserialize(bytes));
                    }
                }
                return jsons;

        });
    }

    public Set<String> zPopBySocre(final String key, final double min,
                                   final double max, final int count) {

        return redisTemplate.execute((RedisConnection connection) -> {
                Set<String> jsons = new LinkedHashSet<String>();

                Set<byte[]> set = connection.zRevRangeByScore(redisTemplate
                                .getStringSerializer().serialize(key), min, max, 0,
                        count);

                if (set != null) {

                    for (byte[] bytes : set) {

                        connection.zRem(redisTemplate.getStringSerializer()
                                .serialize(key), bytes);

                        jsons.add((String) redisTemplate.getStringSerializer()
                                .deserialize(bytes));
                    }
                }
                return jsons;

        });
    }

    public long zCard(final String key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.zCard(redisTemplate.getStringSerializer()
                        .serialize(key));
        });
    }

    public Set<String> zRangeByScore(final String key, final double begin, final double end) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Set<String> set = new LinkedHashSet<String>();
                Set<byte[]> result = connection.zRangeByScore(redisTemplate.getStringSerializer().serialize(key), begin, end);
                if (!CollectionUtils.isEmpty(result)) {
                    for (byte[] bytes : result) {
                        set.add((String) redisTemplate.getStringSerializer().deserialize(bytes));
                    }
                }
                return set;
        });
    }

    public Set<String> zRange(final String key, final long begin, final long end) {

        return redisTemplate.execute((RedisConnection connection) -> {
                Set<String> jsons = new LinkedHashSet<String>();
                Set<byte[]> set = connection.zRange(redisTemplate
                        .getStringSerializer().serialize(key), begin, end);
                if (set != null) {
                    for (byte[] bytes : set) {
                        jsons.add((String) redisTemplate.getStringSerializer()
                                .deserialize(bytes));
                    }
                }
                return jsons;

        });
    }

    public Set<Tuple> zRangeWithScore(final String key, final long begin,
                                      final long end) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Set<Tuple> jsons = new LinkedHashSet<Tuple>();

                Set<org.springframework.data.redis.connection.RedisZSetCommands.Tuple> temp = connection
                        .zRangeWithScores(redisTemplate.getStringSerializer()
                                .serialize(key), begin, end);
                if (temp != null) {
                    for (org.springframework.data.redis.connection.RedisZSetCommands.Tuple tuple : temp) {
                        jsons.add(new Tuple((String) redisTemplate
                                .getStringSerializer().deserialize(
                                        tuple.getValue()), tuple.getScore()));
                    }
                }
                return jsons;
        });
    }

    public void setEx(final String key, final String value, final long seconds) {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.setEx(
                        redisTemplate.getStringSerializer().serialize(key),
                        seconds,
                        redisTemplate.getStringSerializer().serialize(value));
                return true;
        });
    }

    public String get(final String key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                byte[] bytes = connection.get(redisTemplate
                        .getStringSerializer().serialize(key));
                return redisTemplate.getStringSerializer().deserialize(bytes);
        });
    }

    public void del(final String... keys) {
        redisTemplate.execute((RedisConnection connection) -> {
                for (String string : keys) {
                    connection.del(redisTemplate.getStringSerializer()
                            .serialize(string));
                }
                return true;
        });
    }

    public boolean exists(final String key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.exists(redisTemplate.getStringSerializer()
                        .serialize(key));
        });
    }

    public long dbSize() {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.dbSize();
        });
    }

    public void flushAll() {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.flushAll();
                return true;
        });
    }

    public void set(final String key, final String value) {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.set(
                        redisTemplate.getStringSerializer().serialize(key),
                        redisTemplate.getStringSerializer().serialize(value));
                return true;
        });
    }


    public void hDel(final String key, final String... fields) {
        redisTemplate.execute((RedisConnection connection) -> {
                for (String string : fields) {
                    connection.hDel(redisTemplate.getStringSerializer()
                            .serialize(key), redisTemplate
                            .getStringSerializer().serialize(string));
                }
                return true;
        });
    }

    public boolean hExists(final String key, final String field) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.hExists(redisTemplate.getStringSerializer()
                        .serialize(key), redisTemplate.getStringSerializer()
                        .serialize(field));
        });
    }

    public String hGet(final String key, final String field) {
        return redisTemplate.execute((RedisConnection connection) -> {
                byte[] bytes = connection.hGet(redisTemplate
                        .getStringSerializer().serialize(key), redisTemplate
                        .getStringSerializer().serialize(field));
                return redisTemplate.getStringSerializer().deserialize(bytes);
        });
    }

    public Map<String, String> hGet(final String key, final String... field) {
        return redisTemplate.execute((RedisConnection connection) -> {
                Map<String, String> map = new LinkedHashMap<String, String>();
                for (String string : field) {
                    byte[] bytes = connection.hGet(
                            redisTemplate.getStringSerializer().serialize(key),
                            redisTemplate.getStringSerializer().serialize(
                                    string));

                    map.put(string, redisTemplate.getStringSerializer()
                            .deserialize(bytes));
                }
                return map;
        });
    }

    public long hLen(final String key) {
        return redisTemplate.execute((RedisConnection connection) -> {
                return connection.hLen(redisTemplate.getStringSerializer()
                        .serialize(key));
        });
    }

    public void hmSet(final String key, final Map<String, String> map) {
        redisTemplate.execute((RedisConnection connection) -> {
                for (Entry<String, String> entry : map.entrySet()) {
                    connection.hSet(
                            redisTemplate.getStringSerializer().serialize(key),
                            redisTemplate.getStringSerializer().serialize(
                                    entry.getKey()),
                            redisTemplate.getStringSerializer().serialize(
                                    entry.getValue()));
                }
                return true;
        });
    }

    public void hSet(final String key, final String field, final String value) {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.hSet(
                        redisTemplate.getStringSerializer().serialize(key),
                        redisTemplate.getStringSerializer().serialize(field),
                        redisTemplate.getStringSerializer().serialize(value));
                return true;
        });
    }

    public void subscribe(final MessageListener listener,
                          final String... channels) {
        redisTemplate.execute((RedisConnection connection) -> {
                for (String channel : channels) {
                    connection.subscribe(listener, redisTemplate
                            .getStringSerializer().serialize(channel));
                }
            return true;
        });
    }

    public void publish(final String channel, final String message) {
        redisTemplate.execute((RedisConnection connection) -> {
                connection.publish(redisTemplate.getStringSerializer()
                        .serialize(channel), redisTemplate
                        .getStringSerializer().serialize(message));

                return true;
        });
    }

    public Long sCard(final String key){
        return redisTemplate.execute((RedisConnection connection) -> {
            return connection.sCard(redisTemplate.getStringSerializer().serialize(key));

        });
    }

}
