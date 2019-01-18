package com.wip.factory;

/**
 * 工厂方法测试类
 * @author KyrieCao
 * @date 2019/1/18 15:45
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        LoggerFactory factory;
        Logger logger;
        // 文件记录器
        factory = new FileLoggerFactory();
        logger = factory.createLogger();
        logger.writeLog();

        // 数据库记录器
        factory = new DatabaseLoggerFactory();
        logger = factory.createLogger();
        logger.writeLog();
    }
}
