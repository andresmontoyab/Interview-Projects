package co.ceiba.calculateinsuranceservice.rabbit;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("emu.rmq.cloudamqp.com");
        cachingConnectionFactory.setUsername("bjcqecdj");
        cachingConnectionFactory.setPassword("YMuxJTWy0q8tMabGzdp1Rvsy7tyWdAqv");
        //cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setVirtualHost("bjcqecdj");
        cachingConnectionFactory.setChannelCheckoutTimeout(10000);
        cachingConnectionFactory.setRequestedHeartBeat(30);
        return cachingConnectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueueNames("co.ceiba.insuranceRequested");
        messageListenerContainer.setMessageListener(new RabbitConsumer());
        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return messageListenerContainer;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }
}
