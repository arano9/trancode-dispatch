package com.arano.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * RefreshListener
 * * 监听应用刷新事件
 * * <ul>
 * *     <li>刷新tran code映射</li>
 * * </ul>
 *
 * @author arano
 * @since 2021/7/23 14:34
 */
@Component
public class RefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        TranCodeHolder.refreshTranCode();
    }
}
