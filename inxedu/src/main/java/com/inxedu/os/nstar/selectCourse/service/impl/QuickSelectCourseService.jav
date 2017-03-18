package com.inxedu.os.nstar.selectLession.service.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.nstar.selectLession.entity.selectCourse.*;
import com.inxedu.os.nstar.selectLession.service.SelectLessionService;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Created by karak on 16-8-3.
 */
@Service("QuickSelectCourseService")
public class QuickSelectCourseService extends GenericDaoImpl implements SelectLessionService {
    @Override
    public boolean isValidCourseId(Integer courseId, Integer classId) {
        return false;
    }
    public boolean isValidCourseId(final Integer studentId,final Integer courseId,  final String weekId, final String sessionId, final Integer classId){

    }

    @Override
    public String[] getValidCourseIds(String weekId, String sessionId, Integer classId) {
        return new String[0];
    }

    @Override
    public SelectCourseState backCourse(Integer studentId, Integer courseId, Integer classId) {
        return null;
    }

    @Override
    public SelectCourseState backCourse(Integer studentId, Integer courseId, String weekId, String sessionId, Integer classId) {
        return null;
    }


    public SelectCourseState backCourseCore(final Integer studentId, final Integer courseId){

    }
    public SelectCourseState selectCourseCore(final Integer studentId, final Integer courseId){

    }

    @Override
    public SelectCourseState selectCourseWithCourseController(Integer studentId, Integer courseId, Integer classId, String weekId, String sessionId) {
        return null;
    }

    @Override
    public SelectCourseState selectCourse(Integer studentId, Integer courseId, String weekId, String sessionId, Integer classId) {
        return null;
    }

    @Override
    public SelectCourseState backCourseWithCourseController(Integer studentId, Integer courseId, Integer classId, String weekId, String sessionId) {
        return null;
    }

    private final RedisTemplate<String, String> db1;
    private final RedisTemplate<String, String> db2;

    private final DefaultRedisScript<Object> selectLessionScript;
    private final DefaultRedisScript<Object> backLessionScript;


    private final ValueOperations<String, String> courseId_residaulCount;
    private final SetOperations<String, String> studentId_CourseId_Set;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private final SelectLessionService selectLessionService;
    private  CourseController courseController;
    private static Pattern pattern = Pattern.compile(",");

    public boolean[][] showValidCourseLocation(final Integer classId) {
        //缓存
        final String key = "showValidCourseLocation_" + classId.toString();
        Object result = get(key);
        if (ObjectUtils.isNotNull(result)) {
            return (boolean[][]) result;
        } else {
            boolean[][] booleans = selectLessionService.showValidCourseLocation(classId);
            put(key, booleans);
            return booleans;
        }
    }

    public List<Course> showValidCourseInfo(final String weekId, final String sessionId, final Integer classId) {
        Optional<int[]> ids = getValidCourseInfoids(weekId, sessionId, classId);
        if (ids.isPresent()) {
            ArrayList<Course> result = new ArrayList<>();
            int[] ints = ids.get();
            int size = ints.length;
            for (int i = 0; i < size; ++i) {
                Optional<Course> courseOptional = getStaticCourseWithRedisCountByCourseId(ints[i]);
                if (courseOptional.isPresent()) {
                    result.add(courseOptional.get());
                }
            }
            return result;
        } else {
            return ListUtils.EMPTY_LIST;
        }
    }

    public SelectCourseState selectCourse(final Integer courseId, final Integer studentId, final Integer classId) {
        SelectLessionService.SelectCourseState result;
        try {
            result = this.privateSelectCourse(courseId, studentId, classId);
        } catch (Exception e) {
            result = SelectLessionService.SelectCourseState.FAIL;
        }
        return result;
    }

    public SelectCourseState selectCourseCore(final Integer courseId, final Integer studentId, Integer classId) {
        final SelectCourseState state = this.selectLesson(studentId, courseId);
        if (state == SelectCourseState.SUCCESS) {
            log.info("=> redis选课成功 selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
            executorService.execute(() -> {
                log.info("=> 开始持久化MySQL selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
                SelectCourseState result = SelectCourseState.FAIL;
                try {
                    result = selectLessionService.selectCourse(courseId, studentId, classId);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                switch (result) {
                    case SUCCESS: {
                        log.info("=> redis和MySQL选课成功 selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
                        break;
                    }
                    default: {
                        log.error("=> redis选课成功,但持久化MySQL 失败 redis开始退课 selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
                        SelectCourseState backResult = SelectCourseState.FAIL;
                        try {
                            backResult = this.backLesson(studentId, courseId);
                            //由于数据库有事务,失败会回滚,不需要调用数据库
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                        if (backResult == SelectCourseState.SUCCESS) {
                            log.error("=> redis选课成功,但持久化MySQL 失败 redis退课成功 selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
                        } else {
                            log.error("=> 严重错误 持久化MySQL 失败 redis退课失败 selectCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
                        }
                    }
                }
            });
        }
        return state;
    }

    public SelectCourseState backCourseCore(final Integer studentId, final Integer courseId, final Integer classId) {
        SelectCourseState redis = this.backLesson(studentId, courseId);
        SelectCourseState mysql = selectLessionService.backCourse(studentId, courseId, classId);
        if (redis == SelectCourseState.SUCCESS && mysql == SelectCourseState.SUCCESS) {
            log.info("=>  持久化MySQL 退课成功 redis退课成功 backCourseCore studentId:{} courseId:{} classId:{}", studentId, courseId, classId);
            return mysql;
        } else {
            log.error("=> 严重错误 持久化MySQL 失败 redis退课失败 backCourseCore studentId:{} courseId:{} classId:{} redis:{} mysql:{}", studentId, courseId, courseId, redis.name(), mysql.name());
            return SelectCourseState.FAIL;
        }

    }

    public List<Course> showMyCourse(final int userId) {
        final Set<String> list = studentId_CourseId_Set.members(String.valueOf(userId));
        final List<Course> result = new ArrayList<>();
        if (ObjectUtils.isNotNull(list)) {
            for (String id : list) {
                //缓存
                Optional<Course> courseOptional = getStaticCourseByCourseId(Integer.valueOf(id));
                if (courseOptional.isPresent()) {
                    result.add(courseOptional.get());
                }
            }
        }
        return ListUtils.EMPTY_LIST;
    }


    public SelectCourseState privateSelectCourse(final Integer courseId, final Integer studentId, final Integer classId) {
        Optional<String> ids = getCourseIdsByCourseTableDoByClassId(classId);
        if (ids.isPresent()) {
            final String id = courseId.toString();
            if (ids.get().contains(id)) {
                //缓存
                return selectCourseCore(courseId, studentId, classId);
            }
            return SelectCourseState.ILLEGAL;
        }
        return SelectCourseState.FAIL;
    }

    public SelectCourseState selectCourseWithCourseController(final Integer courseId, final Integer studentId, final Integer classId) {
        SelectLessionService.SelectCourseState result;
        if (checkTime()) {
            try {
                result = this.privateSelectCourse(courseId, studentId, classId);
            } catch (Exception e) {
                result = SelectLessionService.SelectCourseState.FAIL;
            }
        } else {
            result = SelectCourseState.ILLEGAL_TIME;
        }
        return result;
    }

    public boolean checkTime() {
        Date now = new Date();
        Date b0 = courseController.getBeginCourseSignupTime0();
        Date e0 = courseController.getEndCourseSignupTime0();
        Date b1 = courseController.getBeginCourseSignupTime1();
        Date e1 = courseController.getEndCourseSignupTime1();
        if (b0.before(now) && e0.after(now) || b1.before(now) && e1.after(now)) {
            return true;
        }
        return false;
    }

    public boolean isSelectCourse(Integer userId) {
        return studentId_CourseId_Set.size(userId.toString()) == 0L;
    }

    public SelectCourseState backCourseWithCourseController(final Integer courseId, final Integer studentId, final Integer classId) {
        SelectLessionService.SelectCourseState result;
        if (checkTime()) {
            try {
                result = this.privateBackCourse(courseId, studentId, classId);
            } catch (Exception e) {
                result = SelectLessionService.SelectCourseState.FAIL;
            }
        } else {
            result = SelectCourseState.ILLEGAL_TIME;
        }
        return result;
    }

    public SelectCourseState privateBackCourse(final Integer courseId, final Integer studentId, final Integer classId) {
        //缓存
        Optional<String> ids = getCourseIdsByCourseTableDoByClassId(classId);
        if (ids.isPresent()) {
            final String id = courseId.toString();
            if (ids.get().contains(id)) {
                return backCourseCore(courseId, studentId, classId);
            }
            return SelectCourseState.ILLEGAL;
        }
        return SelectCourseState.FAIL;
    }

    Optional<int[]> getValidCourseInfoids(final String weekId, final String sessionId, final Integer classId) {
        final String key = "showValidCourseInfo_" + weekId + sessionId + "_" + classId;
        int[] courseList = (int[]) get(key);
        if (ObjectUtils.isNotNull(courseList)) {
            return Optional.of(courseList);
        } else {
            final Map<String, String> map = new HashMap<>(3);
            map.put("weekId", weekId);
            map.put("sessionId", sessionId);
            map.put("classId", classId.toString());
            final String str = sqlSession.selectOne("CourseTableDoMapper.selectCourseIds", map);
            if (StringUtils.isNotEmpty(str)) {
                final String[] ids = pattern.split(str);
                final int size = ids.length;
                final int[] buff = new int[ids.length];
                for (int i = 0; i < size; ++i) {
                    buff[i] = Integer.valueOf(ids[i]);
                }
                put(key, buff);
            } else {
                return Optional.empty();
            }
            return Optional.empty();
        }

    }

    Optional<Course> getStaticCourseByCourseId(final Integer courseId) {
        final String static_course = "staticCourse_courseId_";
        final String key = static_course + Integer.valueOf(courseId);
        Course course = ((Course) get(key));
        if (ObjectUtils.isNotNull(course)) {
            return Optional.of(course);
        } else {
            course = sqlSession.selectOne("CourseMapper.queryCourseById", courseId);
            if (ObjectUtils.isNotNull(course)) {
                put(key, course);
            }
            return Optional.ofNullable(course);
        }
    }

    Optional<Course> getStaticCourseWithRedisCountByCourseId(final Integer courseId) {
        return getStaticCourseByCourseId(courseId).map((course -> {
            String count = courseId_residaulCount.get(courseId);
            if (StringUtils.isNotEmpty(count)) {
                course.setResidaulCount(Integer.valueOf(count));
            } else {
                return null;
            }
            return course;
        }));
    }

    Optional<String> getCourseIdsByCourseTableDoByClassId(final Integer classId) {
        final String courseTableDo = "courseIds_classId_";
        final String key = courseTableDo + classId.toString();
        String ids = (String) get(key);
        if (ObjectUtils.isNull(ids)) {
            try {
                final CourseTableDo eduCourseTableDo = sqlSession.selectOne("CourseTableDoMapper.selectCourseByClassId", classId);
                put(key, eduCourseTableDo.getCourseIds());
                return Optional.ofNullable(eduCourseTableDo.getCourseIds());
            } catch (Throwable e) {
                Optional.empty();
            }
        }
        return Optional.of(ids);
    }

    private Object get(String key) {
        return EHCacheUtil.get(prefix + key);
    }

    private void put(String key, Object object) {
        EHCacheUtil.set(prefix + key, object);
    }
    /*
     数据库取不出数据,系统初始化失败
      */
    public boolean initQuickSelectCourse() {
        if (!initCounter()) return false;
        if (!initDate()) return false;
        if (!initPersonSet()) return false;
        STATE=true;
        return true;
    }
    private boolean initPersonSet() {
        try {
            final  List<StudentIdWithCourseId> list= sqlSession.selectList("SelectCourseMapper.getAllStudentIdWithCourseId",null);
            if (ObjectUtils.isNull(list) || list.isEmpty()) {
                log.error("=> 数据库的学生已选课集合里没有数据");
                return false;
            }
            final RedisSerializer<String> serializer = db1.getStringSerializer();
            try {
                List<Object> result = db2.executePipelined(new RedisCallback<String>() {
                    @Override
                    public String doInRedis(final RedisConnection conn) throws DataAccessException {
                        list.forEach((x) -> {
                            byte[] id = serializer.serialize(x.getStudentId());
                            byte[] courseId = serializer.serialize(x.getCourseId());
                            conn.sAdd(id,courseId);
                        });
                        return null;
                    }
                }, serializer);

            } catch (Throwable throwable) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean initCounter() {
        final List<CourseIdWithResidaulCount> courseIdWithResidaulCounts = sqlSession.selectList("SelectCourseMapper.getAllCourseIdWithResidaulCount", null);
        if (ObjectUtils.isNull(courseIdWithResidaulCounts) || courseIdWithResidaulCounts.isEmpty()) {
            log.error("=> 数据库的课程里没有数据");
            return false;
        }
        final RedisSerializer<String> serializer = db1.getStringSerializer();
        try {
            List<Object> list = db1.executePipelined(new RedisCallback<String>() {
                @Override
                public String doInRedis(final RedisConnection conn) throws DataAccessException {
                    courseIdWithResidaulCounts.forEach((x) -> {
                        byte[] id = serializer.serialize(x.getId());
                        byte[] residaulCount = serializer.serialize(x.getResidaulCount());
                        conn.set(id, residaulCount);
                    });
                    return null;
                }
            }, serializer);

        } catch (Throwable throwable) {
            return false;
        }
        return true;
    }

    private boolean initDate() {
        try {
            this.courseController = sqlSession.selectOne("CourseControllerMappper.selectController");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void stopQuickSelectCourse() {
        STATE=false;
        executorService.shutdown();
    }



    public final SelectCourseState selectLesson(final Integer studentId, final Integer lessonId) {
        List res = null;
        try {
            res = (List) db1.execute(selectLessionScript, key, studentId, lessonId);
        } catch (Exception e) {
            info("studentId:{},lessonId:{},严重错误", studentId, lessonId);
            return SelectCourseState.FAIL;
        }
        switch (res.size()) {
            case 0: {
                info("{}已经选过{}课程了", studentId, lessonId);
                return SelectCourseState.REPLY;
            }
            case 1: {
                Long rest;
                try {
                    rest = (Long) res.get(0);
                } catch (Exception e) {
                    info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
                    return SelectCourseState.FAIL;
                }
                if (0L > rest) {
                    info("studentId:{},lessonId:{},严重错误,选课后余下课程容量:{}", studentId, lessonId, res);
                    return SelectCourseState.FAIL;
                } else {
                    info("{}成功选{}课程", studentId, lessonId);
                    return SelectCourseState.SUCCESS;
                }
            }
            default: {
                info("studentId:{},lessonId:{},严重错误", studentId, lessonId);
                return SelectCourseState.FAIL;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public final SelectCourseState backLesson(final Integer studentId, final Integer lessonId) {
        List res = null;
        try {
            res = (List) db1.execute(selectLessionScript, key, studentId, lessonId);
        } catch (Exception e) {
            info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
            return SelectCourseState.FAIL;
        }
        switch (res.size()) {
            case 0: {
                info("studentId:{},lessonId:{},学生选课集合中没有lessonId,result:{}", studentId, lessonId, res);
                return SelectCourseState.FAIL;
            }
            case 1: {
                Long rest;
                try {
                    rest = (Long) res.get(0);
                } catch (Exception e) {
                    info("studentId:{},lessonId:{},严重错误,result:{}", studentId, lessonId, res);
                    return SelectCourseState.FAIL;
                }
                if (0L > rest) {
                    info("studentId:{},lessonId:{},严重错误,退课后余下课程容量:{}", studentId, lessonId, res);
                    return SelectCourseState.FAIL;
                } else {
                    info("{}成功退{}课程", studentId, lessonId);
                    return SelectCourseState.SUCCESS;
                }
            }
            default: {
                info("studentId:{},lessonId:{},严重错误", studentId, lessonId);
                return SelectCourseState.FAIL;
            }
        }
    }

    private static final Logger log = LoggerFactory.getLogger(QuickSelectCourseService.class);
    private final List<String> key;
    private final String prefix = "select_";
    public static volatile boolean STATE;

    @Autowired
    public QuickSelectCourseService(@Qualifier(value = "dataCounterConnectionFactory") JedisConnectionFactory counter, @Qualifier(value = "dataSetConnectionFactory") JedisConnectionFactory set, @Qualifier("SelectLessionService") SelectLessionService selectLessionService) throws Exception {

        this.selectLessionService = selectLessionService;
        this.db1 = redisTemplate(counter);
        this.db2 = redisTemplate(set);

        this.courseId_residaulCount = this.db1.opsForValue();
        this.studentId_CourseId_Set = this.db2.opsForSet();

        this.key = Collections.singletonList("key");

        this.selectLessionScript = new DefaultRedisScript<>();
        this.selectLessionScript.setResultType(Object.class);

        this.backLessionScript = new DefaultRedisScript<>();
        this.backLessionScript.setResultType(Object.class);

        this.selectLessionScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/lua/selectLession.lua")));
        this.backLessionScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/lua/backLession.lua")));

    }

    public static RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
        return template;
    }

    private static void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }

    private void info(String var2, Object... var3) {
        log.info(var2, var3);
    }

}
