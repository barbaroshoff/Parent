package com.indomdi.com.core.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
public class JmsErrorHandler implements ErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String JMS_ERROR = "JMS_ERROR";

    @Override
    public void handleError(Throwable t) {
        logger.error(JmsErrorHandler.JMS_ERROR, t);
    }
}
