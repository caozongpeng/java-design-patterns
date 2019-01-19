# 建造者（Factory Method）
建造者模式（Builder Pattern） 又名生成器模式，是一种对象构建模式。它可以将复杂对象的建造过程抽象出来（抽象类别），使这个抽象过程的不同实现方法可以构造出不同表现（属性）的对象。

建造者模式是一步一步创建一个复杂的对象，它允许用户只通过指定复杂对象的类型和内容就可以构建它们，用户不需要知道内部的具体构建细节。

## 适用场景
需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。

需要生成的产品对象的属性相互依赖，需要指定其生成顺序。

对象的创建过程独立于创建该对象的类。在建造者模式中通过引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类和客户类中。

隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。

#### 抽象工厂模式VS建造者模式
抽象工厂模式实现对产品家族的创建，一个产品家族是这样的一系列产品，具有不同分类维度的产品组合，采用抽象工厂模式不需要关心构建过程，只关心什么产品由什么工厂生产即可，而建造者模式则是要求按照指定的蓝图建造产品，它的主要目的是通过组装零配件而产生一个新产品。

#### 建造者模式结构图

![builder1](https://github.com/caozongpeng/javaDesignPatterns/blob/master/images/builder1.jpg)

#### 建造者模式主要四个角色
* **Product（产品角色）**：一个具体的产品对象。
* **Builder（抽象建造者）**：创建一个Product对象的各个部件指定的抽象接口。 
* **ConcreteBuilder（具体建造者）**：实现抽象接口，构建和装配各个部件。
* **Director（指挥者）**：构建一个使用Builder接口的对象。它主要是用于创建一个复杂的对象。它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：负责控制产品对象的生产过程。

## 具体实现
使用建造者模式实现汽车的创建。

#### 产品角色（Product）
一个具体的产品对象
```java
/**
 * 汽车产品角色（Product）
 * @author KyrieCao
 * @date 2019/1/19 22:19
 */
public class Car {
    /**
     * 车名
     */
    private String name;

    /**
     * 轮胎
     */
    private String tyreShoe;

    /**
     * 发动机
     */
    private String engine;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyreShoe() {
        return tyreShoe;
    }

    public void setTyreShoe(String tyreShoe) {
        this.tyreShoe = tyreShoe;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
```
#### 抽象建造者（Builder）
创建一个**Product**对象的各个部件指定的抽象接口
```java
/**
 * 汽车抽象建造者（Builder）
 * @author KyrieCao
 * @date 2019/1/19 22:24
 */
public abstract class AbstractCarBuilder {
    /**
     * 创建汽车
     */
    Car car = new Car();
    /**
     * 构建名称
     */
    public abstract void buildName();
    /**
     * 构建轮胎
     */
    public abstract void buildTyreShoe();
    /**
     * 构建发动机
     */
    public abstract void buildEngine();

    /**
     * 构建汽车
     * @return 返回汽车对象
     */
    public Car getCar() {
        return car;
    }
}
```
#### 具体建造者（ConcreteBuilder）
实现抽象接口，构建和装配各个部件
* BMW
```java
/**
 * 宝马 具体建造者（ConcreteBuilder）
 * @author KyrieCao
 * @date 2019/1/19 22:32
 */
public class BmwCar extends AbstractCarBuilder {
    @Override
    public void buildName() {
        car.setName("宝马X6");
    }
    @Override
    public void buildTyreShoe() {
        car.setTyreShoe("225/55 R17");
    }
    @Override
    public void buildEngine() {
        car.setEngine("1.5T 直列3缸 涡轮增压");
    }
}
```
* Benz
```java
/**
 * @author KyrieCao
 * @date 2019/1/19 22:39
 */
public class BenzCar extends AbstractCarBuilder {

    @Override
    public void buildName() {
        car.setName("奔驰倾情特别版");
    }
    @Override
    public void buildTyreShoe() {
        car.setTyreShoe("205/60 R16");
    }
    @Override
    public void buildEngine() {
        car.setEngine("1.3T 直列4缸 涡轮增压");
    }
}
```
#### 指挥者（Director）
构建一个使用Builder接口的对象。它主要是用于创建一个复杂的对象，它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：负责控制产品对象的生产过程。
```java
/**
 * 汽车指挥者（Director）
 * @author KyrieCao
 * @date 2019/1/19 22:44
 */
public class CarWaiter {
    private AbstractCarBuilder carBuilder;

    public CarWaiter(AbstractCarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    /**
     * 构建汽车
     * @return 具体汽车
     */
    public Car construct() {
        // 构建轮胎
        carBuilder.buildTyreShoe();
        // 构建发动机
        carBuilder.buildEngine();
        // 构建车名
        carBuilder.buildName();
        // 组装完毕，返回完整汽车
        return carBuilder.getCar();
    }
}
```
#### 测试类
```java
/**
 * 建造者测试类
 * @author KyrieCao
 * @date 2019/1/19 22:49
 */
public class BuilderTest {
    public static void main(String[] args) {
        // 宝马
        BmwCar bmw = new BmwCar();
        // 宝马汽车服务员
        CarWaiter bmwWaiter = new CarWaiter(bmw);

        // 获得宝马汽车
        Car bmwCar = bmwWaiter.construct();
        System.out.println("汽车组装完毕");
        System.out.println("车名：" + bmwCar.getName() + "; " + "轮胎：" + bmwCar.getTyreShoe() + "; " + "发动机：" + bmwCar.getEngine());

        // 奔驰
        BenzCar benz = new BenzCar();
        // 奔驰汽车服务员
        CarWaiter benzWaiter = new CarWaiter(benz);
        // 获得奔驰汽车
        Car benzCar = benzWaiter.construct();
        System.out.println("汽车组装完毕");
        System.out.println("车名：" + benzCar.getName() + "; " + "轮胎：" + benzCar.getTyreShoe() + "; " + "发动机：" + benzCar.getEngine());
    }
}
```
#### 输出结果
```
汽车组装完毕
车名：宝马X6; 轮胎：225/55 R17; 发动机：1.5T 直列3缸 涡轮增压
汽车组装完毕
车名：奔驰倾情特别版; 轮胎：205/60 R16; 发动机：1.3T 直列4缸 涡轮增压
```
可以看到`CarWaiter`充当指挥者，`AbstractCarBuilder`充当抽象建造者，`BenzCar`和`BmwCar`充当具体建造者。
#### 总结
建造者模式的核心在于如何一步步构建一个包含多个组成部件的完整对象，使用相同的构建过程建不同的产品，在软件开发中，如果我们需要创建复杂对象并希望系统具备很好的灵活性和可扩展性可以考虑使用建造者模式。
* 优点

在建造者模式中，客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。

每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者，用户使用不同的具体建造者即可得到不同的产品对象。由于指挥者类针对抽象建造者编程，增加新的具体建造者无须修改原有类库的代码，系统扩展方便，符合**开闭原则**。

可以更加精细地控制产品的创建过程。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。

* 缺点

建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，例如很多组成部分都不相同，不适合使用建造者模式，因此其使用范围受到一定的限制。

如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大，增加系统的理解难度和运行成本。







