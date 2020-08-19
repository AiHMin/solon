package org.noear.solon.core;

/**
 * 事务策略
 *
 * 事务组下属的任何事务，都可以try+catch处理而不影响整体事务
 * */
public enum TranPolicy {
    /**
     * 必须
     *
     * 1.如果当前存在同源事务则并入，且不主动提交事务；
     * 2.否则新建事务并加入根事务组（如果根级为事务组）
     * */
    required,

    /**
     * 必须新起一个事务
     *
     * 1.且不加入任何事务组（与父事务回滚隔离）
     * */
    requires_new,

    /**
     * 支持但不必须
     *
     * 1.如果当前存在同源事务则并入，且不主动提交；
     * 2.否则不使用事务；
     * */
    supports,

    /**
     * 排除，当前有事务则挂起
     * */
    exclude,

    /**
     * 决不，当前有事务则异常
     * */
    never,

}
