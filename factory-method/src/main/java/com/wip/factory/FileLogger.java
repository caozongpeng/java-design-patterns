package com.wip.factory;

/**
 * 文件日志记录器类（具体产品）
 * @author KyrieCao
 * @date 2019/1/18 15:37
 */
public class FileLogger implements Logger {
    public void writeLog() {
        System.out.println("文件日志记录");
    }
}
