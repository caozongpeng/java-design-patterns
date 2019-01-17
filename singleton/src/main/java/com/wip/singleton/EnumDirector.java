package com.wip.singleton;

/**
 * 饿汉式（枚举方式）
 * @author KyrieCao
 * @date 2019/1/17 17:03
 */
public enum EnumDirector {

    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
