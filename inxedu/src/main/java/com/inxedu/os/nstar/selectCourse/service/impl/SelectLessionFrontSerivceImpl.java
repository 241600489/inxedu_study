package com.inxedu.os.nstar.selectCourse.service.impl;


import com.inxedu.os.nstar.selectCourse.service.SelectLessionFrontSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by karak on 16-6-19.
 */
@Service
public class SelectLessionFrontSerivceImpl implements SelectLessionFrontSerivce {

    private static final Logger log = LoggerFactory.getLogger(SelectLessionFrontSerivceImpl.class);
    private final List<String> key;

    private final RedisTemplate<String, String> writeTemplate;
    private final RedisTemplate<String, String> readTemplate;


    private final DefaultRedisScript<Object> selectLessionScript;
    private final DefaultRedisScript<Object> backLessionScript;


    private final ValueOperations<String, String> writeLessionList;
    private final ValueOperations<String, String> readLessionList;

    private final SetOperations<String, String> readSet;

    @Autowired
    public SelectLessionFrontSerivceImpl(RedisTemplate<String, String> writeTemplate, RedisTemplate<String, String> readTemplate) throws Exception {

        this.writeTemplate = writeTemplate;
        this.readTemplate = readTemplate;
        this.writeLessionList = writeTemplate.opsForValue();
        this.readLessionList = readTemplate.opsForValue();

        this.key = Collections.singletonList("key");

        this.selectLessionScript = new DefaultRedisScript<>();
        this.selectLessionScript.setResultType(Object.class);

        this.backLessionScript = new DefaultRedisScript<>();
        this.backLessionScript.setResultType(Object.class);

        this.selectLessionScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/selectLession.lua")));
        this.backLessionScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/backLession.lua")));

        this.readSet = readTemplate.opsForSet();
    }

    public final void putLessionSize(final Long lessonId, final Integer size) throws Exception {
        if (size >= 0) {
            writeLessionList.set(lessonId.toString(), size.toString());
            info("{}:课程容量设置:{}成功", lessonId, size);
        } else {
            info("{}:课程容量设置:{}失败", lessonId, size);
            throw new Exception("课程容量设置失败");
        }
    }

    public final void putLessionSize(final Long lessonId) throws Exception {
        writeLessionList.set(lessonId.toString(), "0");
    }

    public final void removeLession(final Long lessonId) throws Exception {
        writeTemplate.delete(lessonId.toString());
        info("{}课程被删除", lessonId);
    }

    @SuppressWarnings("unchecked")
    public final void selectLesson(final Long studentId, final Long lessonId, final Consumer<String> successCallback, final Consumer<String> errorCallback) {
        List res = null;
        try {
            res = (List) writeTemplate.execute(selectLessionScript, key, studentId, lessonId);
        } catch (Exception e) {
            info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
            errorCallback.accept(e.toString());
            return;
        }
        switch (res.size()) {
            case 0: {
                info("{}已经选过{}课程了", studentId, lessonId);
                errorCallback.accept("r");
                break;
            }
            case 1: {
                Long rest;
                try {
                    rest = (Long) res.get(0);
                } catch (Exception e) {
                    info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
                    errorCallback.accept(res.toString());
                    return;
                }
                if (0L > rest) {
                    info("studentId:{},lessonId:{},严重错误,选课后余下课程容量:{}", studentId, lessonId, res);
                    errorCallback.accept(rest.toString());
                } else {
                    info("{}成功选{}课程", studentId, lessonId);
                    successCallback.accept("");
                }
                break;
            }
            default: {
                info("studentId:{},lessonId:{},严重错误", studentId, lessonId);
                errorCallback.accept(res.toString());
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public final void backLesson(final Long studentId, final Long lessonId, final Consumer<String> successCallback, final Consumer<String> errorCallback) {
        List res = null;
        try {
            res = (List) writeTemplate.execute(selectLessionScript, key, studentId, lessonId);
        } catch (Exception e) {
            info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
            errorCallback.accept(e.toString());
            return;
        }
        switch (res.size()) {
            case 0: {
                info("studentId:{},lessonId:{},学生选课集合中没有lessonId,result:{}", studentId, lessonId, res);
                errorCallback.accept("r");
                break;
            }
            case 1: {
                Long rest;
                try {
                    rest = (Long) res.get(0);
                } catch (Exception e) {
                    info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
                    errorCallback.accept(res.toString());
                    return;
                }
                if (0L > rest) {
                    info("studentId:{},lessonId:{},严重错误,退课后余下课程容量:{}", studentId, lessonId, res);
                    errorCallback.accept(rest.toString());
                } else {
                    info("{}成功退{}课程", studentId, lessonId);
                    successCallback.accept("");
                }
                break;
            }
            default: {
                info("studentId:{},lessonId:{},严重错误", studentId, lessonId);
                errorCallback.accept(res.toString());
                break;
            }
        }
    }

    @Override
    public Set<String> getLessionIdSetByStudentId(Long studentId) {
        return null;
    }

    public void deleteLession(final Long lessonId) {

    }


    private void info(String var2, Object... var3) {
        log.info(var2, var3);
    }
}
