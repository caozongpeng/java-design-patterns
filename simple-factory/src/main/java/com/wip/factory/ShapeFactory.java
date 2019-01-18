package com.wip.factory;

/**
 * 形状工厂类
 * @author KyrieCao
 * @date 2019/1/18 14:14
 */
public class ShapeFactory {

    /**
     * 获取形状类型对象
     * @param shapeName     形状名称
     * @return 具体形状
     */
    public static Shape getShape(String shapeName) {
        if (null == shapeName) {
            return null;
        }
        if ("CIRCLE".equalsIgnoreCase(shapeName)) {
            return new Circle();
        } else if ("RECTANGLE".equalsIgnoreCase(shapeName)) {
            return new Rectangle();
        } else if ("SQUARE".equalsIgnoreCase(shapeName)) {
            return new Square();
        }
        return null;
    }
}
