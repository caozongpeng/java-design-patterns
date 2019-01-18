package com.wip.factory;

/**
 * 正方形类
 * @author KyrieCao
 * @date 2019/1/18 14:12
 */
public class Square implements Shape {
    public Square() {
        System.out.println("正方形");
    }
    @Override
    public void draw() {
        System.out.println("画正方形");
    }
}
