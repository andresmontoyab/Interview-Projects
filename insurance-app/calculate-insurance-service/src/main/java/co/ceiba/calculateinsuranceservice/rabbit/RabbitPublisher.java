package co.ceiba.calculateinsuranceservice.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class RabbitPublisher {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RabbitConfig.class);
    RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

    public void publishMessageAsync(String exchange, String routingKey, String message) {
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey, message));
    }
}