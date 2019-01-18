package com.wip.factory;

/**
 * 文件日志记录器工厂类（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:43
 */
public class FileLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        return new FileLogger();
    }
}
