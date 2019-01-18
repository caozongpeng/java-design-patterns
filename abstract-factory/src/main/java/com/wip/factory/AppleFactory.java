package com.wip.factory;

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
