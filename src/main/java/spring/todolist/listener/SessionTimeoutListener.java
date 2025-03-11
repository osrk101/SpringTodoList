package spring.todolist.listener;

import org.springframework.stereotype.Component;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
@Component
public class SessionTimeoutListener implements HttpSessionListener {
    private static final int SESSION_TIMEOUT = 30 * 60 ; // 30分

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}