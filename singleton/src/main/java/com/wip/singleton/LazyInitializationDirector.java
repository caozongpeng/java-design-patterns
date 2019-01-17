package com.wip.singleton;

/**
 * 懒汉式（静态内部类方式线程安全）
 * @author KyrieCao
 * @date 2019/1/17 16:53
 */
public final class LazyInitializationDirector {

    private LazyInitializationDirector() {}

    /**
     * 延迟加载
     */
    private static class InstanceHolder {
        private static final LazyInitializationDirector INSTANCE = new LazyInitializationDirector();
    }

    public static LazyInitializationDirector getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
