package com.wip.builder;

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
