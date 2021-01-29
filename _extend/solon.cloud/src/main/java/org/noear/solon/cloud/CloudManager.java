package org.noear.solon.cloud;

import org.noear.solon.cloud.annotation.CloudConfig;
import org.noear.solon.cloud.annotation.CloudEvent;
import org.noear.solon.cloud.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 云接口管理器
 *
 * @author noear
 * @since 1.2
 */
public class CloudManager {
    /**
     * 云端发现服务
     */
    private static CloudDiscoveryService discoveryService;
    /**
     * 云端配置服务
     */
    private static CloudConfigService configService;
    /**
     * 云端事件服务
     */
    private static CloudEventService eventService;
    /**
     * 云端日志服务
     */
    private static CloudLogService logService;

    /**
     * 云端跟踪服务（链路）
     */
    private static CloudTraceService traceService;

    protected final static Map<CloudConfig, CloudConfigHandler> configHandlerMap = new LinkedHashMap<>();
    protected final static Map<CloudEvent, CloudEventHandler> eventHandlerMap = new LinkedHashMap<>();

    /**
     * 登记配置订阅
     */
    public static void register(CloudConfig anno, CloudConfigHandler handler) {
        configHandlerMap.put(anno, handler);
    }

    /**
     * 登记事件订阅
     */
    public static void register(CloudEvent anno, CloudEventHandler handler) {
        eventHandlerMap.put(anno, handler);
    }

    /**
     * 登记云端配置服务
     */
    public static void register(CloudConfigService service) {
        configService = service;
        System.out.println("[CLOUD] CloudConfigService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记云端注册服务
     */
    public static void register(CloudDiscoveryService service) {
        discoveryService = service;
        System.out.println("[CLOUD] CloudDiscoveryService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记云端事件服务
     */
    public static void register(CloudEventService service) {
        eventService = service;
        System.out.println("[CLOUD] CloudEventService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记云端日志服务
     */
    public static void register(CloudLogService service) {
        logService = service;

        System.out.println("[CLOUD] CloudLogService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记云端跟踪服务
     */
    public static void register(CloudTraceService service) {
        traceService = service;

        System.out.println("[CLOUD] CloudTraceService registered from the " + service.getClass().getTypeName());
    }


    protected static CloudConfigService configService() {
        if (configService == null) {
            throw new IllegalStateException("CloudConfigService unregistered");
        } else {
            return configService;
        }
    }

    protected static CloudDiscoveryService discoveryService() {
        if (discoveryService == null) {
            throw new IllegalStateException("CloudDiscoveryService unregistered");
        } else {
            return discoveryService;
        }
    }

    protected static CloudEventService eventService() {
        if (eventService == null) {
            throw new IllegalStateException("CloudEventService unregistered");
        } else {
            return eventService;
        }
    }

    protected static CloudLogService logService() {
        if (logService == null) {
            throw new IllegalStateException("CloudLogService unregistered");
        } else {
            return logService;
        }
    }

    protected static CloudTraceService traceService() {
        if (traceService == null) {
            throw new IllegalStateException("CloudTraceService unregistered");
        } else {
            return traceService;
        }
    }
}
