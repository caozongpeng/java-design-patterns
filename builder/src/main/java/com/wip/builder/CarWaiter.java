package com.wip.builder;

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
