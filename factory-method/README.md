# 工厂方法（Factory Method）
工厂方法（Factory Method）模式，又称多态性工厂（Polymorphic Factory）模式或虚拟构造子（Virtual Constructor）模式。

工厂方法模式应该是在工厂模式家族中是用的最多模式，一般项目中存在最多的就是这个模式。

工厂方法模式是简单工厂的仅一步深化， 在工厂方法模式中，我们不再提供一个统一的工厂类来创建所有的对象，而是针对不同的对象提供不同的工厂。也就是说 **每个对象都有一个与之对应的工厂** 。

## 适用场景
* 一个类不知道它所需要的对象的类：在工厂方法模式中，客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，具体的产品对象由具体工厂类创建，客户端需要知道创建具体产品的工厂类。
* 一个类通过其子类指定创建哪个对象：在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其确定具体要创建的对象，利用面向对象的多态性和里氏原则。
* 将创建对象的任务委托给多个工厂的某一个，客户端在使用时可以无需关心是哪一个工厂子类创建产品子类，需要时再动态指定，可将具体工厂类的类名存储在配置文件或数据库中。


#### 工厂方法模式角色分配

* **抽象工厂(Abstract Factory)角色**：是工厂方法模式的核心，与应用程序无关。任何在模式中创建的对象的工厂类必须实现这个接口。
* **具体工厂(Concrete Factory)角色** ：这是实现抽象工厂接口的具体工厂类，包含与应用程序密切相关的逻辑，并且受到应用程序调用以创建某一种产品对象。
* **抽象产品(AbstractProduct)角色** ：工厂方法模式所创建的对象的超类型，也就是产品对象的共同父类或共同拥有的接口。
* **具体产品(Concrete Product)角色** ：这个角色实现了抽象产品角色所定义的接口。某具体产品有专门的具体工厂创建，它们之间往往一一对应。

## 具体实现
使用工厂方法创建一个日志记录器。

#### 日志记录器接口（抽象产品）
```java
/**
 * 日志记录器接口（抽象产品）
 * @author KyrieCao
 * @date 2019/1/18 15:35
 */
public interface Logger {
    /**
     * 输出日志
     */
    void writeLog();
}
```

#### 数据库日志记录器（具体产品）
```java
/**
 * 数据库日志记录器类（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 15:36
 */
public class DatabaseLogger implements Logger {
    public void writeLog() {
        System.out.println("数据库日志记录");
    }
}
```

#### 文件日志记录器（具体产品）
```java
/**
 * 文件日志记录器类（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 15:37
 */
public class FileLogger implements Logger {
    public void writeLog() {
        System.out.println("文件日志记录");
    }
}
```

#### 日志记录器工厂接口（抽象工厂）
```java
/**
 * 日志记录器工厂接口（抽象工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:38
 */
public interface LoggerFactory {
    /**
     * 创建日志记录器产品
     * @return 具体产品
     */
    Logger createLogger();
}
```

#### 数据库日志记录器工厂类（具体工厂）
```java
/**
 * 数据库日志记录器工厂类（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:41
 */
public class DatabaseLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        return new DatabaseLogger();
    }
}
```

#### 文件日志记录器工厂类（具体工厂）
```java
/**
 * 文件日志记录器工厂类（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:43
 */
public class FileLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        return new FileLogger();
    }
}
```
#### 测试类
```java
/**
 * 工厂方法测试类
 * @author KyrieCao
 * @date 2019/1/18 15:45
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        LoggerFactory factory;
        Logger logger;
        // 文件记录器
        factory = new FileLoggerFactory();
        logger = factory.createLogger();
        logger.writeLog();

        // 数据库记录器
        factory = new DatabaseLoggerFactory();
        logger = factory.createLogger();
        logger.writeLog();
    }
}
```
#### 输出结果
```
文件日志记录
数据库日志记录
```
可以看到LoggerFactory接口充当抽象工厂，其子类DatabaseLoggerFactory和FileLoggerFactory充当具体工厂，Logger接口充当抽象产品，其子类DatabaseLogger和FileLogger充当具体产品。
#### 总结
工厂方法模式是简单工厂模式的延伸，它继承了简单工厂模式的优点，同时还弥补了简单工厂模式的不足。工厂方法模式是使用频率最高的设计模式之一，是很多开源框架和API类库的核心模式。

* 优点

在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。

基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够让工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模式之所以又被称为多态工厂模式，就正是因为所有的具体工厂类都具有同一抽象父类。

使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具体工厂和具体产品就可以了，这样，系统的可扩展性也就变得非常好，完全符合**开闭原则**。

* 缺点

在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，会给系统带来一些额外的开销。

由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统的实现难度。




