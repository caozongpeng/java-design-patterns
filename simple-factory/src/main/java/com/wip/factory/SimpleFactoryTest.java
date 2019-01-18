package com.wip.factory;

/**
 * 简单工厂测试类
 * @author KyrieCao
 * @date 2019/1/18 14:22
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 获取Circle对象，并调用draw方法
        Shape circle = ShapeFactory.getShape("CIRCLE");
        circle.draw();
        // 获取Rectangle对象，并调用draw方法
        Shape rectangle = ShapeFactory.getShape("RECTANGLE");
        rectangle.draw();
        // 获取Square对象，并调用draw方法
        Shape square = ShapeFactory.getShape("SQUARE");
        square.draw();
    }
}
