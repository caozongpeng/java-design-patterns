package com.wip.singleton;

/**
 * 懒汉式（双重检查加锁）
 * @author KyrieCao
 * @date 2019/1/17 16:42
 */
public final class ThreadSafeDoubleCheckLocking {

    /**
     * volatile保证，当INSTANCE变量被初始化成单实例时，多个线程可以正确处理INSTANCE变量
     */
    private static volatile ThreadSafeDoubleCheckLocking INSTANCE;

    private ThreadSafeDoubleCheckLocking() {
        // 防止通过反射进行实例化
        if (null != INSTANCE) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    public static ThreadSafeDoubleCheckLocking getInstance() {
        // 采用局部变量的形式可以提高约 25% 的性能
        ThreadSafeDoubleCheckLocking instance = INSTANCE;
        // 如果已经被实例化则直接返回该实例
        if (null == instance) {
            // 无法确定其他的线程是否已经完成初始化
            // 为了确保我们需要锁定一个对象来进行确认
            synchronized (ThreadSafeDoubleCheckLocking.class) {
                // 再次将实例分配给局部变量，检查它是否被其他线程初始化
                // 在当前线程被阻塞进入锁定区域时。如果它被初始化则直接返回之前创建的实例
                instance = INSTANCE;
                // 如果仍是 null， 才创建实例
                if (null == instance) {
                    INSTANCE = instance = new ThreadSafeDoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
