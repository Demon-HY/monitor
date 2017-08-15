package com.monitor.baseservice.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件的基类，所有事件继承该类
 *
 * Created by yhe.abcft on 2017/7/25.
 */
public class CommonEvent extends ApplicationEvent {

    /**
     * 事件的内容（具有实际业务意义的内容）
     */
    private String eventContent;

    public CommonEvent(Object source) {
        super(source);
    }

    public CommonEvent(Object source, String eventContent) {
        super(source);
        this.eventContent = eventContent;
    }
}
