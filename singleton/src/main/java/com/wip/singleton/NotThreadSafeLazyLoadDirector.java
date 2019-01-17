package com.wip.singleton;

/**
 * 懒汉式（非线程安全）
 * @author KyrieCao
 * @date 2019/1/17 16:20
 */
public final class NotThreadSafeLazyLoadDirector {

    private static NotThreadSafeLazyLoadDirector INSTANCE;

    private NotThreadSafeLazyLoadDirector() {
        // 防止通过反射进行实例化
        if (null != INSTANCE) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    /**
     * 没有加入synchronized关键字的版本是线程不安全的
     * @return 单例实例
     */
    public static NotThreadSafeLazyLoadDirector getInstance() {
        // 判断当前单例是否存在，若存在则返回，不存在则创建
        if (INSTANCE == null) {
            INSTANCE = new NotThreadSafeLazyLoadDirector();
        }
        return INSTANCE;
    }
}