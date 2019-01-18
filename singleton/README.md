# 单例模式（Singleton）

## 用途
保证一个类仅有一个实例，并提供一个访问它的全局访问点，让类自身负责保存它的唯一实例，这个类可以保证没有其他实例可以被创建，并且它可以提供一个访问该实例的方法。

## 解决问题

在我们系统中，有一些对象其实我们只需要一个，比如说：线程池、缓存、对话框、注册表、日志对象、打印机、显卡等对象，这一类对象只能有一个实例，如果创建出多个实例的话可能导致一些问题的产生，比如：程序的行为异常、资源使用过量、或者不一致性的结果。

#### 单例模式带来的好外
* 对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是一笔巨大的开销。
* 由于 `new` 操作的次数减少，因而对系统内存的使用也会降低，这将减轻 GC 压力，缩短 GC 停顿时间。

## 具体实现
#### JAVA中两构建方式
* 饿汉式。指全局的单例实现在类装载时构建。
* 懒汉式。指全局的单例实例在第一次使用时构建。

#### 不管是哪种创建方式，它们通常都存在下面几点相似之处
* 单例类必须要有一个 `private` 访问级别的构造函数，只有这样，才能确保单例不会在的其它代码内被实例化
* `NSTANCE` 成员变量和 `instance` 方法必须是 `static` 的。

### 饿汉式（线程安全）
```java
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
```
所谓 `饿汉式` 就是说JVM在加载这个类时就马上创建此唯一的单例实例，不管你用不用，先创建了再说，如果一直没有被使用，便浪费了空间，典型的空间换时间，每次调用的时候，就不需要再决断，节省了运行时间。

### 懒汉式（非线程安全和synchronized关键字线程安全版本）
```java
/**
 * 懒汉式（非线程安全）
 * @author KyrieCao
 * @date 2019/1/17 16:20
 */
public final class NotThreadSafeLazyLoadDirector {
    
    private static NotThreadSafeLazyLoadDirector INSTANCE;

    private ThreadSafeLazyLoadDirector() {
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
```
所谓 `懒汉式` 就是说单例实例在第一次被使用时构建，而不是在JVM在加载这个类时就马上创建此唯一的单例实例。

上面这种方式很明显是线程不安全的，如果多个线程同时访问 `getInstance()` 方法时就会出现问题。如果想要保证线程安全，一种比较觉的方式就是在`getInstance()`方法前加上 `synchronized` 关键字。
```java
/**
 * 懒汉式（线程安全）
 * @author KyrieCao
 * @date 2019/1/17 16:20
 */
public final class ThreadSafeLazyLoadDirector {
    
    private static ThreadSafeLazyLoadDirector INSTANCE;

    private ThreadSafeLazyLoadDirector() {
        // 防止通过反射进行实例化
        if (null != INSTANCE) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    /**
     * 没有加入synchronized关键字的版本是线程不安全的
     * @return 单例实例
     */
    public static synchronized ThreadSafeLazyLoadDirector getInstance() {
        // 判断当前单例是否存在，若存在则返回，不存在则创建
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafeLazyLoadDirector();
        }
        return INSTANCE;
    }
}
```
我们知道 `synchronized` 关键字偏重量级锁。虽然在 JavaSE1.6之后 `synchronized` 关键字进行了优化，为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁之后效率有了显著提升。

但是在程序中每次使用 `getInstance()` 都要经过 `synchronized` 加锁这一层，这难免会增加 `getInstance()` 方法的时间消费，而且还可能会发生阻塞。下面介绍到 `双重检查加锁版本` 就是为了解决这个问题而存在的。

### 懒汉式（双重检查加锁版本）
利用双重检查加锁（double-checked locking），首先检查是否实例已经创建，如果尚未创建，“才”进行同步。这样以来，只有一次同步，这正是我们想要的效果。
```java
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

```
很明显，这种方式相比于使用`synchronized`关键字的方法，可以大大减少`getInstance()`的时间消费。

### 懒汉式（登记式/静态内部类方式）
静态内部实现的单例是懒加载的且线程安全。
```java
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

```
### 饿汉式（枚举方式）
这是实现单例模式的最佳方法。它理简洁，自动支持序列化机制，绝对防止多次实例化（如果单例类实现了Serializable接口，默认情况下每次反序列化总会创建一新的实例对象，关于单例与序列化的问题可以查看这一篇文章 `http://www.hollischuang.com/archives/1144`）,同时这种方式也是《Effective Java 》以及《Java与模式》的作者推荐的方式。
```java
/**
 * 饿汉式（枚举方式）
 * @author KyrieCao
 * @date 2019/1/17 17:03
 */
public enum EnumDirector {

    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
```
### 测试结果

### 缺点
* 由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
* 单例的职责过重，在一定程度上违背了`单一职责原则`。因为单例类既充当了工厂角色，提供了工厂方法，同时又充当了产品角色，包含一下线业务方法，将产品的创建和产品的本身的功能整合到一起。
### 总结
我们主要介绍到了以下方式实现单例模式
* 饿汉式（线程安全）
* 懒汉式（非线程安全和`synchronized`关键字线程安全版本）
* 懒汉式（双重检查加锁版）
* 懒汉式（登记式/静态内部类方式）
* 饿汉式（枚举方式）
主要应用场景如下
* 一个类频繁的被实例化，但同时又频繁的被销毁
* 一个类的实例化过程非常耗费资源，且该类的对象频繁的被使用
* 类中的方法调用结果和类的状态有关
* 需要频繁访问网络、数据库或文件的类










