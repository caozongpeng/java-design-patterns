# 抽象工厂（Abstract Factory）
抽象工厂（Abstract Factory）模式，又称工具箱（Kit 或Toolkit）模式。

工厂方法模式通过引入工厂等级结构，解决了简单工厂模式中工厂类职责太重的问题，但由于工厂方法模式中的每个工厂只生产一类产品，可能会导致系统中存在大量的工厂类，势必会增加系统的开销。此时，我们可以考虑将一些相关的产品组成一个**产品族**，由同一个工厂来统一生产。

## 适用场景
* 和工厂方法一样客户端不需要知道它所创建的对象的类。
* 需要一组对象共同完成某种功能时，并且可能存在多组对象完成不同功能的情况（同属于同一个产品族的产品）。
* 系统结构稳定，不会频繁的增加对象。（因为一旦增加就需要修改原有代码，不符合开闭原则）
* 产品等级结构稳定，设计完成之后，不会向系统中增加新的产品等级结构或者删除已有的产品等级结构。
#### 抽象工厂方法模式角色分配
* 抽象工厂（AbstractFactory）角色 ：是工厂方法模式的核心，与应用程序无关。任何在模式中创建的对象的工厂类必须实现这个接口。
* 具体工厂（ConcreteFactory）角色 ：这是实现抽象工厂接口的具体工厂类，包含与应用程序密切相关的逻辑，并且受到应用程序调用以创建某一种产品对象。
* 抽象产品（Abstract Product）角色 ：工厂方法模式所创建的对象的超类型，也就是产品对象的共同父类或共同拥有的接口。
* 具体产品（Concrete Product）角色 ：抽象工厂模式所创建的任何产品对象都是某一个具体产品类的实例。在抽象工厂中创建的产品属于同一产品族，这不同于工厂模式中的工厂只创建单一产品。

#### 抽象工厂的工厂和工厂方法中的工厂有什么区别？
抽象工厂是生产一整套有产品的（至少要生产两个产品)，这些产品必须相互是有关系或有依赖的，而工厂方法中的工厂是生产单一产品的工厂。
#### 抽象工厂图示
![abstract-factory](https://github.com/caozongpeng/javaDesignPatterns/blob/master/images/abstract-factory.jpg)

## 具体实现
现在手机基本上人人都有，我们假设现在存在苹果、三星两种手机，每一种手机对应一种手机壳。我们现在这样考虑生产苹果手机的工厂可以顺便生产苹果手机的手机壳，生产三星手机的工厂可以顺便生产三星手机的手机壳（苹果工厂生产苹果系统产品包括手机壳，三星工厂同理）。

![abstract-factory2](https://github.com/caozongpeng/javaDesignPatterns/blob/master/images/abstract-factory2.png)

#### 创建抽象产品接口
* 手机
```java
/**
 * 手机接口（抽象产品）
 * @author KyrieCao
 * @date 2019/1/18 17:14
 */
public interface Phone {
    /**
     * 手机显示
     */
    void show();
}
```
* 手机壳
```java
/**
 * 手机壳接口（抽象产品）
 * @author KyrieCao
 * @date 2019/1/18 17:17
 */
public interface PhoneShell {
    /**
     * 带上手机壳
     */
    void load();
}
```

#### 创建具体抽象产品实现类
* Apple手机类
```java
/**
 * 苹果手机（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 17:19
 */
public class Apple implements Phone {
    public void show() {
        System.out.println("苹果手机");
    }
}
```
* Samsung手机类
```java
/**
 * 三星手机（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 17:20
 */
public class Samsung implements Phone {
    public void show() {
        System.out.println("三星手机");
    }
}
```
* Apple手机壳类
```java
/**
 * 苹果手机壳（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 17:22
 */
public class AppleShell implements PhoneShell {
    public void load() {
        System.out.println("苹果手机壳");
    }
}
```
* Samsung手机壳类
```java
/**
 * 三星手机壳（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 17:23
 */
public class SamsungShell implements PhoneShell {
    public void load() {
        System.out.println("三星手机壳");
    }
}
```
#### 创建工厂接口
```java
/**
 * 工厂接口（抽象工厂）
 * @author KyrieCao
 * @date 2019/1/18 17:25
 */
public interface Factory {
    /**
     * 生产手机
     * @return  具体手机
     */
    Phone producePhone();

    /**
     * 生产手机壳
     * @return  具体手机壳
     */
    PhoneShell producePhoneShell();
}
```
#### 创建具体抽象工厂实现类
* 生产Apple手机和Apple手机壳的工厂
```java
/**
 * 苹果工厂（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 17:29
 */
public class AppleFactory implements Factory {
    public Phone producePhone() {
        return new Apple();
    }

    public PhoneShell producePhoneShell() {
        return new AppleShell();
    }
}
```

* 生产Samsung手机和Samsung手机壳的工厂
```java
/**
 * 三星工厂（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 17:30
 */
public class SamsungFactory implements Factory {
    public Phone producePhone() {
        return new Samsung();
    }

    public PhoneShell producePhoneShell() {
        return new SamsungShell();
    }
}
```
#### 测试类
```java
/**
 * 抽象工厂测试类
 * @author KyrieCao
 * @date 2019/1/18 17:31
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        Factory factory;
        Phone phone;
        PhoneShell phoneShell;

        // 生产苹果手机
        factory = new AppleFactory();
        phoneShell = factory.producePhoneShell();
        phoneShell.load();
        phone = factory.producePhone();
        phone.show();

        // 生产三星手机
        factory = new SamsungFactory();
        phoneShell = factory.producePhoneShell();
        phoneShell.load();
        phone  = factory.producePhone();
        phone.show();
    }
}
```
#### 测试结果
```
苹果手机壳
苹果手机
三星手机壳
三星手机
```
可以看到`Factory`接口充当抽象工厂，其子类`AppleFactory`和`SamsungFactory`充当具体工厂，接口`Phone`和`PhoneShell`充当抽象产品，其子类`Apple`、`Samsung`和`AppleShell`、`SamsungShell`充当具体产品。

## 总结
抽象工厂模式是工厂方法模式的进一步延伸，由于它提供了功能更为强大的工厂类并且具备较好的可扩展性，在软件开发中得以广泛应用，尤其是在一些框架和API类库的设计中，例如在Java语言的AWT（抽象窗口工具包）中就使用了抽象工厂模式，它使用抽象工厂模式来实现在不同的操作系统中应用程序呈现与所在操作系统一致的外观界面。抽象工厂模式也是在软件开发中最常用的设计模式之一。

* 优点

抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易，所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。

当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。

增加新的产品族很方便，无须修改已有系统，符合**开闭原则**。

* 缺点

增加新的产品等级结构麻烦，需要对原有系统进行较大的修改，甚至需要修改抽象层代码，这显然会带来较大的不便，违背了**开闭原则**。