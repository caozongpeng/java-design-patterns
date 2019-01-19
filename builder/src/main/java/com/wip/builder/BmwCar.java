package com.wip.builder;

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
