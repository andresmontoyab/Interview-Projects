package co.ceiba.uploadVideo.uploadVideoService;

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
public class UploadVideoServiceApplication {

	public final static String SFG_MESSAGE_QUEUE = "co.ceiba.insuranceAcepted";

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


	public static void main(String[] args) {
		SpringApplication.run(UploadVideoServiceApplication.class, args);
	}
}
