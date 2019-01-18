package com.wip.factory;

/**
 * 简单工厂测试类
 * @author KyrieCao
 * @date 2019/1/18 14:22
 */
public class SimpleFactoryTest2 {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory2.getClass(Circle.class);
        circle.draw();

        Rectangle rectangle = (Rectangle) ShapeFactory2.getClass(Rectangle.class);
        rectangle.draw();

        Square square = (Square) ShapeFactory2.getClass(Square.class);
        square.draw();
    }
}
