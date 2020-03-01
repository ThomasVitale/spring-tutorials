package com.thomasvitale.application;

import com.thomasvitale.application.multitenancy.TenantContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    @Test
    public void testThread() {
        TenantContext.setCurrentTenant("TENANT_ARGUS");
        System.out.println("(Main) SET Thread name: " + Thread.currentThread().getName() + ", tenantId: " + TenantContext.getCurrentTenant());

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0; i<10; i++) {
            executor.submit(new AsyncTaskParent());
        }

        TenantContext.clear();
        System.out.println("(Main) CLEAR Thread name: " + Thread.currentThread().getName() + ", tenantId: " + TenantContext.getCurrentTenant());
        executor.shutdown();
    }

    static class AsyncTaskParent implements Runnable {

        @Override
        public void run() {
            System.out.println("(AsyncParent) SET Thread name: " + Thread.currentThread().getName() + ", tenantId: " + TenantContext.getCurrentTenant());

            ExecutorService executor = Executors.newCachedThreadPool();
            executor.submit(new AsyncTask());
            TenantContext.clear();
            System.out.println("(AsyncParent) CLEAR Thread name: " + Thread.currentThread().getName() + ", tenantId: " + TenantContext.getCurrentTenant());
            executor.shutdown();
        }
    }

    static class AsyncTask implements Runnable {

        @Override
        public void run() {
            System.out.println("(Async) SET Thread name: " + Thread.currentThread().getName() + ", tenantId: " + TenantContext.getCurrentTenant());
        }
    }
}