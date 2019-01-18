package com.wip.factory;

/**
 * 日志记录器工厂接口（抽象工厂）
 * @author KyrieCao
 * @date 2019/1/18 15:38
 */
public interface LoggerFactory {
    /**
     * 创建日志记录器产品
     * @return 具体产品
     */
    Logger createLogger();
}
