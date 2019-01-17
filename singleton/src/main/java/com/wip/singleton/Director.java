package com.wip.singleton;

/**
 * 饿汉式（线程安全）
 * @author KyrieCao
 * @date 2019/1/17 16:01
 */
public final class Director {

    /**
     * 静态初始化单例实例，这段代码保证了线程安全
     */
    private static final Director INSTANCE = new Director();

    /**
     * Director类只有一个构造方法且被 private 修饰的，无法通过 new 方法创建对象实例
     */
    private Director() {}

    /**
     * 客户端获取单例实例
     * @return 单例实例
     */
    public static Director getInstance() {
        return INSTANCE;
    }
}