package com.inxedu.os.nstar.selectCourse.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by karak on 16-6-18.
 */
public class OneLevelAsyncContext implements InitializingBean {
    private int asyncTimeoutInSeconds = 5;
    private AsyncListener asyncListener;
    private int queueCapacity = 2048;
    private String poolSize = "1-1";
    private BlockingDeque queue;
    private long keepAliveTimeInSeconds = 10L;
    private ThreadPoolExecutor executor;
    private Logger LOG = LoggerFactory.getLogger(OneLevelAsyncContext.class);
    private ObjectMapper mapper = new ObjectMapper();

    public void submitFuture(final HttpServletRequest req, final Callable<?> task) {
        final String uri = req.getRequestURI();
        final Map<String, String[]> params = req.getParameterMap();
        final AsyncContext asyncContext = req.startAsync();  //开启异步上下文
        asyncContext.getRequest().setAttribute("uri", uri);
        asyncContext.getRequest().setAttribute("params", params);
        asyncContext.setTimeout(asyncTimeoutInSeconds * 1000);
        if (asyncListener != null) {
            asyncContext.addListener(asyncListener);
        }
        executor.submit(new OneLevelCallable(asyncContext) { //提交任务给业务线程池
            @Override
            public Object call() throws Exception {
                Object o = task.call();  //业务处理调用
                if (o == null || o instanceof String) {
                    callBack(asyncContext, o, uri, params);  //业务完成后，响应处理
                    return null;
                } else if (o instanceof CompletableFuture) {
                    CompletableFuture<Object> future = (CompletableFuture<Object>) o;
                    future.thenAccept(resultObject -> callBack(asyncContext, resultObject, uri, params))
                            .exceptionally(e -> {
                                callBack(asyncContext, "", uri, params);
                                return null;
                            });
                }
                asyncContext.complete();
                return null;
            }
        });
    }


    private void callBack(AsyncContext asyncContext, Object result, String uri, Map<String, String[]> params) {
        HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
        try {
            if (result instanceof String) {
                write(resp, (String) result);
            } else {
                write(resp, toJSON(result));
            }
        } catch (Throwable e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //程序内部错误
            try {
                LOG.error("get info error, uri : {},  params : {}", uri, toJSON(params), e);
            } catch (Exception ex) {
            }
        } finally {
            asyncContext.complete();
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        String[] poolSizes = poolSize.split("-");
        //初始线程池大小
        int corePoolSize = Integer.valueOf(poolSizes[0]);
        //最大线程池大小
        int maximumPoolSize = Integer.valueOf(poolSizes[1]);
        /*
corePoolSize - 池中所保存的线程数，包括空闲线程。
maximumPoolSize - 池中允许的最大线程数。
keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
unit - keepAliveTime 参数的时间单位。
workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
         */
        queue = new LinkedBlockingDeque<Runnable>(queueCapacity);
        executor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize,
                keepAliveTimeInSeconds, TimeUnit.SECONDS,
                queue);

        executor.allowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof OneLevelCallable) {
                    OneLevelCallable cc = ((OneLevelCallable) r);
                    AsyncContext asyncContext = cc.getAsyncContext();
                    if (asyncContext != null) {
                        try {
                            String uri = (String) asyncContext.getRequest().getAttribute("uri");
                            Map params = (Map) asyncContext.getRequest().getAttribute("params");
                            LOG.error("async request rejected, uri : {}, params : {}", uri, toJSON(params));
                        } catch (Exception e) {
                        }
                        try {
                            HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        } finally {
                            asyncContext.complete();
                        }
                    }
                }
            }
        });

        if (asyncListener == null) {
            asyncListener = new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    onError(event);
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    AsyncContext asyncContext = event.getAsyncContext();
                    try {
                        String uri = (String) asyncContext.getRequest().getAttribute("uri");
                        Map params = (Map) asyncContext.getRequest().getAttribute("params");
                        LOG.error("async request error, uri : {}, params : {}", uri, toJSON(params));
                    } catch (Exception e) {
                    }
                    try {
                        HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    } finally {
                        asyncContext.complete();
                    }
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {

                }
            };
        }

    }

    private void write(HttpServletResponse resp, String result) throws IOException {
        resp.getOutputStream().print(result);
    }

    private String toJSON(Object result) throws IOException {
        return mapper.writeValueAsString(result);
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(String poolSize) {
        this.poolSize = poolSize;
    }

    public long getKeepAliveTimeInSeconds() {
        return keepAliveTimeInSeconds;
    }

    public void setKeepAliveTimeInSeconds(long keepAliveTimeInSeconds) {
        this.keepAliveTimeInSeconds = keepAliveTimeInSeconds;
    }

    public int getAsyncTimeoutInSeconds() {
        return asyncTimeoutInSeconds;
    }

    public void setAsyncTimeoutInSeconds(int asyncTimeoutInSeconds) {
        this.asyncTimeoutInSeconds = asyncTimeoutInSeconds;
    }
}
