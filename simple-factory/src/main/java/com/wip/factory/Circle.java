package com.wip.factory;

/**
 * 圆形类
 * @author KyrieCao
 * @date 2019/1/18 14:08
 */
public class Circle implements Shape {
    public Circle() {
        System.out.println("圆形");
    }
    @Override
    public void draw() {
        System.out.println("画圆");
    }
}
