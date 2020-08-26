package co.ceiba.guardarinsuranceservice.guardar_insurance_service;

import co.ceiba.guardarinsuranceservice.guardar_insurance_service.listener.InsuranceListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GuardarInsuranceServiceApplication {

	public final static String SFG_MESSAGE_QUEUE = "co.ceiba.insuranceAcepted";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("co.ceiba.insurance");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("acepted");
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
	MessageListenerAdapter listenerAdapter(InsuranceListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}


	public static void main(String[] args) {
		SpringApplication.run(GuardarInsuranceServiceApplication.class, args);


	}
}
