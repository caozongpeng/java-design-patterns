package com.wip.factory;

/**
 * 数据库日志记录器类（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 15:36
 */
public class DatabaseLogger implements Logger {
    public void writeLog() {
        System.out.println("数据库日志记录");
    }
}
