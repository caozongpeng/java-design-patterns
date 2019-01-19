package com.wip.builder;

/**
 * 建造者测试类
 * @author KyrieCao
 * @date 2019/1/19 22:49
 */
public class BuilderTest {
    public static void main(String[] args) {
        // 宝马
        BmwCar bmw = new BmwCar();
        // 宝马汽车服务员
        CarWaiter bmwWaiter = new CarWaiter(bmw);

        // 获得宝马汽车
        Car bmwCar = bmwWaiter.construct();
        System.out.println("汽车组装完毕");
        System.out.println("车名：" + bmwCar.getName() + "; " + "轮胎：" + bmwCar.getTyreShoe() + "; " + "发动机：" + bmwCar.getEngine());

        // 奔驰
        BenzCar benz = new BenzCar();
        // 奔驰汽车服务员
        CarWaiter benzWaiter = new CarWaiter(benz);
        // 获得奔驰汽车
        Car benzCar = benzWaiter.construct();
        System.out.println("汽车组装完毕");
        System.out.println("车名：" + benzCar.getName() + "; " + "轮胎：" + benzCar.getTyreShoe() + "; " + "发动机：" + benzCar.getEngine());
    }
}
