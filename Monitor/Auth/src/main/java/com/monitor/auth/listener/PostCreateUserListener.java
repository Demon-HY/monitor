package com.monitor.auth.listener;

import com.monitor.baseservice.event.CommonEvent;

/**
 * Created by yhe.abcft on 2017/7/25.
 */
public class PostCreateUserListener extends CommonEvent {
    public PostCreateUserListener(Object source) {
        super(source);
    }

    public PostCreateUserListener(Object source, String eventContent) {
        super(source, eventContent);
    }
}
