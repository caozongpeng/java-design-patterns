package com.wip.factory;

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
