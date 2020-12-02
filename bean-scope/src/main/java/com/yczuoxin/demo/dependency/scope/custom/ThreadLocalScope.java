package com.yczuoxin.demo.dependency.scope.custom;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread_local_scope";

    private NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread_local") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = threadLocal.get();
        return map.computeIfAbsent(name, (key -> {
            Object object1 = objectFactory.getObject();
            map.put(name, object1);
            return object1;
        }));
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> map = threadLocal.get();
        return map.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        System.out.println("registerDestructionCallback 被调用了...");
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> map = threadLocal.get();
        return map.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
