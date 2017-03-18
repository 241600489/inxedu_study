package com.inxedu.os.common.cache;


import org.springframework.data.redis.connection.MessageListener;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICache {
    /**
     * 删除 缓存
     *
     * @param key
     * @return
     * @
     */
    String deleteCached(byte[] key);

    /**
     * 更新 缓存
     *
     * @param key
     * @param value
     * @return
     * @
     */
    Object updateCached(byte[] key, byte[] value, Long expire);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @
     */
    Object getCached(byte[] key);

    /**
     * 根据 正则表达式key 获取 列表
     *
     * @param keys
     * @return
     * @
     */
    Set<byte[]> getKeys(byte[] keys);

    /**
     * 根据 正则表达式key 获取 列表
     *
     * @param
     * @return
     * @
     */
    Set getHashKeys(byte[] key);

    /**
     * 更新 缓存
     *
     * @param key
     * @param value
     * @return
     * @
     */
    Boolean updateHashCached(byte[] key, byte[] mapkey, byte[] value,
                             Long expire);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @
     */
    Object getHashCached(byte[] key, byte[] mapkey);

    /**
     * 删除 缓存
     *
     * @param
     * @param
     * @return
     * @
     */
    Long deleteHashCached(byte[] key, byte[] mapkey);

    /**
     * 获取 map的长度
     *
     * @param
     * @return
     * @
     */
    Long getHashSize(byte[] key);

    /**
     * 获取 map中的所有值
     *
     * @param
     * @return
     * @
     */
    List getHashValues(byte[] key);

    /**
     * 获取 map的长度
     *
     * @param
     * @return
     * @
     */
    Long getDBSize();

    /**
     * 获取 map的长度
     *
     * @param
     * @return
     * @
     */
    void clearDB();

    public abstract boolean zExists(final String key, final String value);

    /**
     * Add one member to a sorted set, or update its score if it already exists
     *
     * @param key
     * @param score The score values should be the string representation of a
     *              numeric value, and accepts double precision floating point
     *              numbers.
     * @param value
     */
    public abstract void zAdd(final String key, final String value,
                              final double score);

    /**
     * Adds all the specified to a sorted set, or update its score if it already
     * exists
     *
     * @param
     * @param      be the string representation of a
     *              numeric value, and accepts double precision floating point
     *              numbers.
     * @param
     */
    public abstract void zAdd(final String key, final Map<String, Long> map);

    /**
     * Removes the specified members from the sorted set stored at key. Non
     * existing members are ignored.
     *
     * @param key
     * @param member
     */
    public abstract long zRem(final String key, final String member);

    /**
     * Pop the specified range of elements in the sorted set stored at key. The
     * elements are considered to be ordered from the lowest to the highest
     * score. Lexicographical order is used for elements with equal score.
     *
     * @param key
     * @param begin Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @param end   Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @return list of elements in the specified range
     */
    public abstract Set<String> zPop(final String key, final int begin,
                                     final int end);

    /**
     * Returns all the elements in the sorted set at key with a score between
     * min and max (including elements with score equal to min or max). The
     * elements are considered to be ordered from low to high scores.
     *
     * @param key
     * @param min
     * @param max
     * @param count
     * @return
     */
    public abstract Set<String> zPopBySocre(final String key, final double min,
                                            final double max, final int count);

    /**
     * Returns the sorted set cardinality (number of elements) of the sorted set
     * stored at key
     *
     * @param key
     * @return the cardinality (number of elements) of the sorted set, or 0 if
     * key does not exist.
     */
    public abstract long zCard(final String key);

    /**
     * Returns the specified range of elements in the sorted set stored at key.
     * The elements are considered to be ordered from the lowest to the highest
     * score. Lexicographical order is used for elements with equal score.
     *
     * @param key
     * @param begin Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @param end   Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @return list of elements in the specified range.
     */
    public abstract Set<String> zRange(final String key, final long begin,
                                       final long end);

    /**
     * Returns the specified range of elements in the sorted set stored at key.
     * The elements are considered to be ordered from the lowest to the highest
     * score. Lexicographical order is used for elements with equal score.
     *
     * @param key
     * @param begin Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @param end   Both start and stop are zero-based indexes, where 0 is the
     *              first element, 1 is the next element and so on. They can also
     *              be negative numbers indicating offsets from the end of the
     *              sorted set, with -1 being the last element of the sorted set,
     *              -2 the penultimate element and so on. Out of range indexes
     *              will not produce an error. If start is larger than the largest
     *              index in the sorted set, or start > stop, an empty list is
     *              returned. If stop is larger than the end of the sorted set
     *              Redis will treat it like it is the last element of the sorted
     *              set.
     * @return list of elements in the specified range with their scores.
     */
    public abstract Set<Tuple> zRangeWithScore(final String key,
                                               final long begin, final long end);

    /**
     * Set key to hold the string value and set key to timeout after a given
     * number of seconds.
     *
     * @param key
     * @param value
     * @param seconds
     */
    public abstract void setEx(final String key, final String value,
                               final long seconds);

    /**
     * Get the value of key. If the key does not exist the special value null is
     * returned. An error is returned if the value stored at key is not a
     * string, because GET only handles string values.
     *
     * @param key
     * @return
     */
    public abstract String get(final String key);

    /**
     * Set key to hold the string value. If key already holds a value, it is
     * overwritten, regardless of its type. Any previous time to live associated
     * with the key is discarded on successful SET operation.
     *
     * @param key
     * @param value
     */
    public abstract void set(final String key, final String value);

    /**
     * Removes the specified keys. A key is ignored if it does not exist.
     *
     * @param keys
     */
    public abstract void del(final String... keys);

    /**
     * Returns if key exists.
     *
     * @param key
     * @return
     */
    public abstract boolean exists(final String key);

    /**
     * Return the number of keys in the currently-selected database.
     *
     * @return
     */
    public abstract long dbSize();

    /**
     * Delete all the keys of all the existing databases, not just the currently
     * selected one. This command never fails.
     */
    public abstract void flushAll();

    /**
     * Removes the specified fields from the hash stored at key. Specified
     * fields that do not exist within this hash are ignored. If key does not
     * exist, it is treated as an empty hash and this command returns 0.
     *
     * @param key
     * @param fields
     * @return the number of fields that were removed from the hash, not
     * including specified but non existing fields.
     */
    public abstract void hDel(final String key, final String... fields);

    /**
     * Returns if field is an existing field in the hash stored at key.
     *
     * @return
     */
    public abstract boolean hExists(final String key, final String field);

    /**
     * Returns the value associated with field in the hash stored at key.
     *
     * @param key
     * @param field
     * @return the value associated with field, or null when field is not
     * present in the hash or key does not exist.
     */
    public abstract String hGet(final String key, final String field);

    /**
     * Returns the value associated with field in the hash stored at key.
     *
     * @param key
     * @param field
     * @return the value associated with field, or null when field is not
     * present in the hash or key does not exist.
     */
    public abstract Map<String, String> hGet(final String key,
                                             final String... field);

    /**
     * Returns the number of fields contained in the hash stored at key.
     *
     * @param key
     * @return number of fields in the hash, or 0 when key does not exist.
     */
    public abstract long hLen(final String key);

    /**
     * Sets the specified fields to their respective values in the hash stored
     * at key. This command overwrites any existing fields in the hash. If key
     * does not exist, a new key holding a hash is created.
     *
     * @param map
     */
    public abstract void hmSet(final String key, final Map<String, String> map);

    /**
     * Sets field in the hash stored at key to value. If key does not exist, a
     * new key holding a hash is created. If field already exists in the hash,
     * it is overwritten.
     *
     * @param key
     * @param value
     */
    public abstract void hSet(final String key, final String field,
                              final String value);

    /**
     * Subscribes the client to the specified channels.
     *
     * @param listener
     * @param channels
     */
    public abstract void subscribe(final MessageListener listener,
                                   final String... channels);

    /**
     * Posts a message to the given channel.
     *
     * @param channel
     * @param message
     */
    public abstract void publish(final String channel, final String message);

    public Set<String> zRangeByScore(final String key, final double begin, final double end);

    public Long sCard(final String key);
}
