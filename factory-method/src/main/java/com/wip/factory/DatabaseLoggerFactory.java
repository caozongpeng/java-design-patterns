package com.wip.factory;

/**
 * 数据库日志记录器工厂类（具体工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:41
 */
public class DatabaseLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        return new DatabaseLogger();
    }
}
