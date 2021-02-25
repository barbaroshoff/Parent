package com.indomdi.core.config;

import com.indomdi.core.exception.JmsErrorHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import javax.jms.ConnectionFactory;
import java.util.concurrent.ThreadPoolExecutor;

@EnableJms
@Configuration
public class JmsConfig {
    private static final String JMS_TYPE_ID_PROPERTY = "_type";
    public static final String LOGGING_QUEUE = "NOMENO.LOGGING.QUEUE";
    public static final String SIGNUP_DLQ_QUEUE = "NOMENO.SIGNUP.DLQ.QUEUE";

    @Value("${jms.consumers.concurrent.queue:10}")
    private int concurrentQueue;

    @Autowired
    private JmsErrorHandler errorHandler;

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName(JmsConfig.JMS_TYPE_ID_PROPERTY);
        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory factory, MessageConverter messageConverter) {
        final JmsTemplate jmsTemplate = new JmsTemplate(factory);
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    /**
     * Construct JmsListenerContainer
     * <p/>
     *
     * @param connectionFactory   - connection factory
     * @param configurer          - jms listener container factory configurer
     * @param factoryId           - unique factory id
     * @param concurrentConsumers
     * @return a new configured instance of
     * {@link DefaultJmsListenerContainerFactory}
     */
    public DefaultJmsListenerContainerFactory constructJmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                                   DefaultJmsListenerContainerFactoryConfigurer configurer, String factoryId, String concurrentConsumers) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSessionTransacted(true);
        factory.setConcurrency(concurrentConsumers);
        factory.setTaskExecutor(containerFactoryThreadPool(factoryId, concurrentConsumers));
        factory.setErrorHandler(errorHandler);
        configurer.configure(factory, connectionFactory);

        return factory;
    }

    /**
     * Constructs a thread pool task executor based on
     * <code>this.concurrentConsumers</code> parameter
     * <p/>
     *
     * @param id - thread pool id used as thread name prefix
     * @return configured thread pool instance
     */
    public ThreadPoolTaskExecutor containerFactoryThreadPool(String id, String concurrentConsumers) {
        Assert.isTrue(StringUtils.isNotBlank(id), "Pool id is mandatory");
        final String[] poolSize = concurrentConsumers.split("-");

        Assert.isTrue(2 == poolSize.length, "Invalid concurrent consumers parameter : " + concurrentConsumers);
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(poolSize[0]));
        executor.setMaxPoolSize(Integer.parseInt(poolSize[1]));
        executor.setQueueCapacity(concurrentQueue);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("JMS_POOL_" + id + "_");
        executor.initialize();

        return executor;
    }
}
