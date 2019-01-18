package com.wip.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton测试类
 * @author KyrieCao
 * @date 2019/1/17 17:10
 */
public class SingletonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonTest.class);

    public static void main(String[] args) {

        // 饿汉式（线程安全）
        Director director1 = Director.getInstance();
        Director director2 = Director.getInstance();
        LOGGER.info("饿汉式（线程安全）1 = {}", director1);
        LOGGER.info("饿汉式（线程安全）2 = {}", director2);

        // 懒汉式（静态内部类方式线程安全）
        LazyInitializationDirector lazyDirector1 = LazyInitializationDirector.getInstance();
        LazyInitializationDirector lazyDirector2 = LazyInitializationDirector.getInstance();
        LOGGER.info("懒汉式（静态内部类方式线程安全）1 = {}", lazyDirector1);
        LOGGER.info("懒汉式（静态内部类方式线程安全）2 = {}", lazyDirector2);

        // 懒汉式（双重检查加锁）
        ThreadSafeDoubleCheckLocking doubleCheckLocking1 = ThreadSafeDoubleCheckLocking.getInstance();
        ThreadSafeDoubleCheckLocking doubleCheckLocking2 = ThreadSafeDoubleCheckLocking.getInstance();
        LOGGER.info("懒汉式（双重检查加锁）1 = {}", doubleCheckLocking1);
        LOGGER.info("懒汉式（双重检查加锁）2 = {}", doubleCheckLocking2);

        // 懒汉式（线程安全）
        ThreadSafeLazyLoadDirector lazyLoadDirector1 = ThreadSafeLazyLoadDirector.getInstance();
        ThreadSafeLazyLoadDirector lazyLoadDirector2 = ThreadSafeLazyLoadDirector.getInstance();
        LOGGER.info("懒汉式（线程安全）1 = {}", lazyLoadDirector1);
        LOGGER.info("懒汉式（线程安全）2 = {}", lazyLoadDirector2);


        // 饿汉式（枚举方式）
        EnumDirector enumDirector1 = EnumDirector.INSTANCE;
        EnumDirector enumDirector2 = EnumDirector.INSTANCE;
        LOGGER.info("饿汉式（枚举方式）1 = {}", enumDirector1);
        LOGGER.info("饿汉式（枚举方式）2 = {}", enumDirector2);


    }


}
