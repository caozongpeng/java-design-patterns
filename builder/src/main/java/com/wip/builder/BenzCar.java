package com.wip.builder;

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
