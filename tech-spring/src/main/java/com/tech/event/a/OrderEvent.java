package com.tech.event.a;

import org.springframework.context.ApplicationEvent;
import sun.applet.AppletEvent;

/**
 * @author lw
 * @since 2023-05-15
 */
public class OrderEvent extends ApplicationEvent {
    private String name;

    public OrderEvent(Object source,String name) {
        super(source);
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
