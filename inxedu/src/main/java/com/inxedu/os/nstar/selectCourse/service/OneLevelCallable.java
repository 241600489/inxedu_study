package com.inxedu.os.nstar.selectCourse.service;

import javax.servlet.AsyncContext;
import java.util.concurrent.Callable;

/**
 * Created by karak on 16-6-18.
 */
public abstract class OneLevelCallable implements Callable {
    private AsyncContext asyncContext;

   // private boolean callReturnResult=false;
    public OneLevelCallable(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }


    public AsyncContext getAsyncContext() {
        return asyncContext;
    }
}
