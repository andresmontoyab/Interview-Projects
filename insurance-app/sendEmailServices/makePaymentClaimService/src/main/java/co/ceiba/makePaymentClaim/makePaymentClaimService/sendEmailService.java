package co.ceiba.makePaymentClaim.makePaymentClaimService;

import co.ceiba.makePaymentClaim.makePaymentClaimService.listener.ClaimCompletedListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class sendEmailService {

	public final static String SFG_MESSAGE_QUEUE = "co.ceiba.claimCompleted";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("co.ceiba.claim");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("completed");
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("emu.rmq.cloudamqp.com");
		cachingConnectionFactory.setUsername("bjcqecdj");
		cachingConnectionFactory.setPassword("YMuxJTWy0q8tMabGzdp1Rvsy7tyWdAqv");
		cachingConnectionFactory.setVirtualHost("bjcqecdj");
		cachingConnectionFactory.setChannelCheckoutTimeout(10000);
		cachingConnectionFactory.setRequestedHeartBeat(30);
		return cachingConnectionFactory;
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(SFG_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ClaimCompletedListener receiver) {
		return new MessageListenerAdapter(receiver);
	}

	public static void main(String[] args) {
		SpringApplication.run(sendEmailService.class, args);
	}
}
