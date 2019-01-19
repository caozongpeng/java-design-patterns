package com.wip.builder;

/**
 * 汽车产品角色（Product）
 * @author KyrieCao
 * @date 2019/1/19 22:19
 */
public class Car {
    /**
     * 车名
     */
    private String name;

    /**
     * 轮胎
     */
    private String tyreShoe;

    /**
     * 发动机
     */
    private String engine;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyreShoe() {
        return tyreShoe;
    }

    public void setTyreShoe(String tyreShoe) {
        this.tyreShoe = tyreShoe;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
