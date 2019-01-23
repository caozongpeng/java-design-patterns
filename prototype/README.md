# 原型（ProtoType）
原型模式（Prototype Pattern） 在面向对象系统中，使用原型模式来复制一个对象自身，从而克隆出多个与原型对象一模一样的对象。

将一个原型对象传给那个要发动创建的对象，这个要发动创建的对象通过请求原型对象拷贝自己来实现创建过程。由于在软件系统中我们经常会遇到需要创建多个相同或者相似对象的情况，因此原型模式在真实开发中的使用频率还是非常高的。原型模式是一种`另类`的创建型模式，创建克隆对象的工厂就是原型类自身，工厂方法由克隆方法来实现。

需要注意的是通过克隆方法所创建的对象是全新的对象，它们在内存中拥有新的地址，通常对克隆所产生的对象进行修改对原型对象不会造成任何影响，每一个克隆对象都是相互独立的。通过不同的方式修改可以得到一系列相似但不完全相同的对象。

![prototype](https://github.com/caozongpeng/javaDesignPatterns/blob/master/images/prototype.jpg)

## 适用场景
我们现在一般会使用new关键字指定类名生成类的实例（PS：我们以前使用`java.lang.Cloneable`的一个很大原因是使用new创建对象的速度相对来说会慢一些，随着JVM性能的提升，new的速度和Object的`clone（）`方法的速度差不多了。）。

使用new关键字创建类的时候必须指定类名，但是在开发过程中也会有`在不指定类名的前提下生成实例`的需求。

**1) 对象种类繁多，无法将他们整合到一个类的时候；**

**2) 难以根据类生成实例时；**

**3) 想解耦框架与生成的实例时。**

如果想要让生成实例的框架不再依赖于具体的类，这时，不能指定类名来生成实例，而要事先`注册`一个`原型`实例，然后通过复制该实例来生成新的实例。

#### 模式分析
在原型模式结构中定义了一个抽象原型类，所有的Java类都继承自`java.lang.Object`，而Object类提供一个`clone()`方法，可以将一个Java对象复制一份。因此在Java中可以直接使用Object提供的clone()方法来实现对象的克隆，Java语言中的原型模式实现很简单。

能够实现克隆的Java类必须实现一个标识接口`Cloneable`，表示这个Java类支持复制。如果一个类没有实现这个接口但是调用了`clone()`方法，Java编译器将抛出一个`CloneNotSupportedException`异常。

**注意： `java.lang.Cloneable` 只是起到告诉程序可以调用clone方法的作用，它本身并没有定义任何方法**。

在使用原型模式克隆对象时，**根据其成员对象是否也克隆**，原型模式可以分为两种形式：**深克隆** 和 **浅克隆**。


#### 原型模式主要三个角色
* **Prototype（抽象原型类）**：它是声明克隆方法的接口，是所有具体原型类的公共父类，可以是抽象类也可以是接口，甚至还可以是具体实现类。
* **ConcretePrototype（具体原型类）**：它实现在抽象原型类中声明的克隆方法，在克隆方法中返回自己的一个克隆对象。
* **Client（客户类）**：让一个原型对象克隆自身从而创建一个新的对象，在客户类中只需要直接实例化或通过工厂方法等方式创建一个原型对象，再通过调用该对象的克隆方法即可得到多个相同的对象。由于客户类针对抽象原型类Prototype编程，因此用户可以根据需要选择具体原型类，系统具有较好的可扩展性，增加或更换具体原型类都很方便。

## 具体实现
使用原型模式实现工作周报的创建。
#### 浅克隆
在浅克隆中，如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。简单来说，在浅克隆中，当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制。

* 附件类
```java
/**
 * 周报附件类
 * @author KyrieCao
 * @date 2019/1/23 22:07
 */
public class Attachment {
    /**
     * 附件名
     */
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void download() {
        System.out.println("下载附件，文件名为：" + name);
    }
}
```
* 工作周报类
```java
/**
 * 具体原型类(ConcretePrototype)
 *
 * @author KyrieCao
 * @date 2019/1/23 21:42
 */
public class WeeklyLog implements Cloneable {
    private String name;
    private String date;
    private String content;

    /**
     * 为了简化设计和实现，假设一份工作周报中只有一个附件对象，实际情况中可以包含多个附件，可以通过List等集合对象来实现
     */
    private Attachment attachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    /**
     * 克隆方法clone()，此处使用Java语言提供的克隆机制
     * @return weeklyLog
     */
    @Override
    public WeeklyLog clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeeklyLog) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
            return null;
        }
    }
}
```
* 测试类
```java
/**
 * 客户类(Client)
 * @author KyrieCao
 * @date 2019/1/23 21:49
 */
public class Client {
    public static void main(String[] args) {
        // 创建原型对象
        WeeklyLog logPreviousOld,logPreviousNew;
        // 创建原型对象
        logPreviousOld = new WeeklyLog();
        // 创建附件对象
        Attachment attachment = new Attachment();
        // 将附件添加到周报中
        logPreviousOld.setAttachment(attachment);
        // 调用克隆方法创建克隆对象
        logPreviousNew = logPreviousOld.clone();
        // 比较周报
        System.out.println("周报是否相同：" + (logPreviousOld == logPreviousNew));
        // 比较附件
        System.out.println("附件是否相同：" + (logPreviousOld.getAttachment() == logPreviousNew.getAttachment()));

    }
}
```
* 输出结果
```
周报是否相同：false
附件是否相同：true
```
由于使用的是浅克隆技术，因此工作周报对象复制成功，通过`==`比较原型对象和克隆对象的内存地址时输出`false`；但是比较附件对象的内存地址时输出`true`，说明它们在内存中是同一个对象。

#### 深克隆
在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，深克隆将原型对象的所有引用对象也复制一份给克隆对象。简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制。

###### 深克隆实现方式
如果需要实现深克隆，可以通过序列化(Serialization)等方式来实现。序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，而原对象仍然存在于内存中。通过序列化实现的拷贝不仅可以复制对象本身，而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，再从流里将其读出来，可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，否则无法实现序列化操作。下面我们使用深克隆技术来实现工作周报和附件对象的复制，由于要将附件对象和工作周报对象都写入流中，因此两个类均需要实现Serializable接口。

* 修改附件类`Attachment`
```java
class Attachment implements Serializable {
    // 其它不变
}
```
* 修改工作周报类`WeeklyLog`
工作周报类WeeklyLog不再使用Java自带的克隆机制，而是通过序列化来从头实现对象的深克隆，我们需要重新编写clone()方法。
```java
/**
 * 具体原型类(ConcretePrototype)
 *
 * @author KyrieCao
 * @date 2019/1/23 21:42
 */
public class WeeklyLog implements Serializable {
    
    // 其它不变

    /**
     * 使用序列化技术实现深克隆
     * @return
     */
    public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
        // 将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        // 将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (WeeklyLog) ois.readObject();
    }

}
```
* 测试类
```java
/**
 * 客户类(Client)
 * @author KyrieCao
 * @date 2019/1/23 21:49
 */
public class Client {
    public static void main(String[] args) {
        // 创建原型对象
        WeeklyLog logPreviousOld,logPreviousNew = null;
        // 创建原型对象
        logPreviousOld = new WeeklyLog();
        // 创建附件对象
        Attachment attachment = new Attachment();
        // 将附件添加到周报中
        logPreviousOld.setAttachment(attachment);
        // 调用克隆方法创建克隆对象
        try {
            logPreviousNew = logPreviousOld.deepClone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 比较周报
        System.out.println("周报是否相同：" + (logPreviousOld == logPreviousNew));
        // 比较附件
        System.out.println("附件是否相同：" + (logPreviousOld.getAttachment() == logPreviousNew.getAttachment()));
    }
}
```
* 输出结果
```
周报是否相同：false
附件是否相同：false
```
从输出结果可以看出，由于使用了深克隆技术，附件对象也得以复制，因此用“==”比较原型对象的附件和克隆对象的附件时输出结果均为false。深克隆技术实现了原型对象和克隆对象的完全独立，对任意克隆对象的修改都不会给其他对象产生影响，是一种更为理想的克隆实现方式。

**再次强调**：Java语言提供的Cloneable接口和Serializable接口的代码非常简单，它们都是空接口，这种空接口也称为标识接口，标识接口中没有任何方法的定义，其作用是告诉JRE这些接口的实现类是否具有某个功能，如是否支持克隆、是否支持序列化等。

#### 总结
原型模式作为一种快速创建大量相同或相似对象的方式，在软件开发中应用较为广泛，很多软件提供的复制`(Ctrl + C)`和粘贴`(Ctrl + V)`操作就是原型模式的典型应用，下面对该模式的使用效果和适用情况进行简单的总结。
* 优点

当创建新的对象实例较为复杂时，使用原型模式可以简化对象的创建过程，通过复制一个已有实例可以提高新实例的创建效率。

扩展性较好，由于在原型模式中提供了抽象原型类，在客户端可以针对抽象原型类进行编程，而将具体原型类写在配置文件中，增加或减少产品类对原有系统都没有任何影响。

原型模式提供了简化的创建结构，工厂方法模式常常需要有一个与产品类等级结构相同的工厂等级结构，而原型模式就不需要这样，原型模式中产品的复制是通过封装在原型类中的克隆方法实现的，无须专门的工厂类来创建产品。

可以使用深克隆的方式保存对象的状态，使用原型模式将对象复制一份并将其状态保存起来，以便在需要的时候使用（如恢复到某一历史状态），可辅助实现撤销操作。

* 缺点

需要为每一个类配备一个克隆方法，而且该克隆方法位于一个类的内部，当对已有的类进行改造时，需要修改源代码，违背了**开闭原则**。

在实现深克隆时需要编写较为复杂的代码，而且当对象之间存在多重的嵌套引用时，为了实现深克隆，每一层对象对应的类都必须支持深克隆，实现起来可能会比较麻烦。













