package com.wip.factory;

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
