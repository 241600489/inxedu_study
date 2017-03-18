package com.inxedu.os.nstar.selectCourse.service;



import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by karak on 16-7-18.
 */
public interface SelectLessionFrontSerivce {
    void putLessionSize(final Long lessonId, final Integer size) throws Exception;

    void putLessionSize(final Long lessonId) throws Exception;

    void selectLesson(final Long studentId, final Long lessonId, final Consumer<String> successCallback, final Consumer<String> errorCallback);

    void backLesson(final Long studentId, final Long lessonId, final Consumer<String> successCallback, final Consumer<String> errorCallback);



    Set<String> getLessionIdSetByStudentId(final Long studentId);

    void deleteLession(final Long lessonId);

}
