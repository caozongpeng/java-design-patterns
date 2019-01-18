package com.wip.factory;

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
