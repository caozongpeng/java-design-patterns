package com.wip.factory;

/**
 * 长方形类
 * @author KyrieCao
 * @date 2019/1/18 14:10
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("长方形");
    }
    @Override
    public void draw() {
        System.out.println("画长方形");
    }
}
