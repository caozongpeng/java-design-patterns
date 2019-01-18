# 简单工厂（Simple Factory）
简单工厂（Simple Factory）模式，又称静态工厂方法模式（Static Factory Method Pattern）。

严格的说，简单工厂模式并不是23种常用的设计模式之一，它只算工厂模式的一个特殊实现。简单工厂模式在实际中的应用相对于其他2个工厂模式用的还是相对少得多，因为它只适应很多简单的情况。

最重要的是它违背了我们在概述中说的 开放-封闭原则 （虽然可以通过反射的机制来避免） 。因为每次你要新添加一个功能，都需要在生switch-case 语句（或者if-else 语句）中去修改代码，添加分支条件。
## 适用场景
* 工厂类负责创建的对象比较少，由于创建的对象较少，不会造成工厂方法中的业务逻辑太过复杂。
* 客户端只知道传入工厂类的参数，对于如何创建对象并不关心。
## 简单工厂模式角色分配
* **工厂（Factory）角色**：简单工厂模式的核心，它负责实现创建所有实例的内部逻辑，工厂类可以被外界直接调用，创建所需的产品对象。
* **抽象产品（Product）角色**：简单工厂模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。
* **具体产品（Concrete Product）角色**：简单工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。

## 具体实现
创建一个可以绘制不同形状的绘图工具，可以绘制圆形，正文形，三角形，每个图形都会有一个 draw() 方法用于绘图。
#### 创建Shape接口
```java
/**
 * 形状抽象接口
 * @author KyrieCao
 * @date 2019/1/18 14:06
 */
public interface Shape {
    /**
     * 绘制方法
     */
    void draw();
}
```
#### 创建实现绘画接口的具体图形类
* 圆形
```java
/**
 * 圆形类
 * @author KyrieCao
 * @date 2019/1/18 14:08
 */
public class Circle implements Shape {
    public Circle() {
        System.out.println("圆形");
    }
    @Override
    public void draw() {
        System.out.println("画圆");
    }
}
```
* 长方形
```java
/**
 * 长方形类
 * @author KyrieCao
 * @date 2019/1/18 14:10
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("长方形");
    }
    @Override
    public void draw() {
        System.out.println("画长方形");
    }
}
```

* 正方形
```java
/**
 * 正方形类
 * @author KyrieCao
 * @date 2019/1/18 14:12
 */
public class Square implements Shape {
    public Square() {
        System.out.println("正方形");
    }    
    @Override
    public void draw() {
        System.out.println("画正方形");
    }
}
```
#### 创建工厂类
```java
/**
 * 形状工厂类
 * @author KyrieCao
 * @date 2019/1/18 14:14
 */
public class ShapeFactory {

    /**
     * 获取形状类型对象
     * @param shapeName     形状名称
     * @return 具体形状
     */
    public static Shape getShape(String shapeName) {
        if (null == shapeName) {
            return null;
        }
        if ("CIRCLE".equalsIgnoreCase(shapeName)) {
            return new Circle();
        } else if ("RECTANGLE".equalsIgnoreCase(shapeName)) {
            return new Rectangle();
        } else if ("SQUARE".equalsIgnoreCase(shapeName)) {
            return new Square();
        }
        return null;
    }
}
```
#### 测试方法
```java
/**
 * 简单工厂测试类
 * @author KyrieCao
 * @date 2019/1/18 14:22
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 获取Circle对象，并调用draw方法
        Shape circle = ShapeFactory.getShape("CIRCLE");
        circle.draw();
        // 获取Rectangle对象，并调用draw方法
        Shape rectangle = ShapeFactory.getShape("RECTANGLE");
        rectangle.draw();
        // 获取Square对象，并调用draw方法
        Shape square = ShapeFactory.getShape("SQUARE");
        square.draw();
    }
}
```
#### 输出结果
```
圆形
画圆
长方形
画长方形
正方形
画正方形
```
可以看出`Shape`接口充当抽象产品类，其子类 `Circle`、`Rectangle`和`Square`充当具体产品类，`ShapeFactory`充当工厂类。

这样实现明显看出有问题，如果我们新增产品类的话，就需要修改工厂类中的 `getShape()` 方法，这很明显不符合 **开方-封闭原则**，
#### 使用反射机制改善简单工厂
修改工厂类
```java
/**
 * 反射形状工厂类
 * @author KyrieCao
 * @date 2019/1/18 14:39
 */
public class ShapeFactory {
    public static Object getClass(Class<? extends Shape> clazz) {
        Object object = null;

        try {
            object = Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }    
}
```
#### 测试方法
```java
/**
 * 简单工厂测试类
 * @author KyrieCao
 * @date 2019/1/18 14:22
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory.getClass(Circle.class);
        circle.draw();

        Rectangle rectangle = (Rectangle) ShapeFactory.getClass(Rectangle.class);
        rectangle.draw();

        Square square = (Square) ShapeFactory.getClass(Square.class);
        square.draw();
    }
}
```
测试结果依然不变，这种方式虽然符合了**开放-关闭原则**，但是每一次传入的都是产品类的全部路径，这样比较麻烦，如果需要改善的话可以通过**反射+配置文件**的形式来改善，这种方式使用的比较多。

## 总结
简单工厂模式提供了专门的工厂类用于创建对象，将对象的创建和对象的使用分离开，它作为一种最简单的工厂模式在软件开发中得到了较为广泛的应用。
 
* 优点

工厂类包含必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，客户端直接创建产品对象的职责，而仅仅消费产品，简单工厂模式实现了对象创建和使用的分离。

客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，对于一些复杂的类名，通过简单工厂模式可以在一定程度减少使用者的记忆量。

通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。

* 缺点

由于工厂类集中了所有产品的创建逻辑，职责过重，一旦不能正常工作，整个系统都要受到影响。

使用简单工厂模式势必会增加系统中类的个数（引入了新的工厂类），增加了系统的复杂度和理解难度。

系统扩展困难，一旦添加新产品就不得不修改工厂逻辑，在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展和维护。

简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。

