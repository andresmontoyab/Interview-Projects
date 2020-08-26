package co.ceiba.calculateinsuranceservice.rabbit;

import co.ceiba.calculateinsuranceservice.dto.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.core.Message;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class RabbitConsumerTest {

    RabbitConsumer rabbitConsumer;
    Message message;

    @Before
    public void setUp() throws Exception {
        rabbitConsumer = new RabbitConsumer();
        message = new Message(("{\"address\" : \"Diagonal 55\", \"stratum\" : 3, \"value\" : 100000000, \"type\" : \"HOUSE\"}").getBytes(), new MessageProperties());
    }

    @Test
    public void testGetData() {
        //Given//When
        String data = rabbitConsumer.getData(message);
        //Then
        String valueExpected = "{\"id\":\"\",\"user\":{\"id\":null,\"name\":null,\"email\":null,\"cellphone\":null},\"property\":{\"address\":\"Diagonal 55\",\"stratum\":3,\"value\":100000000,\"type\":\"HOUSE\"},\"insuranceValue\":420833.3333333334}";

        assertEquals(valueExpected, data);
    }

    @Test
    public void testOnMessage() {
        //Given
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        RabbitConsumer rabbitConsumerSpy = Mockito.spy(new RabbitConsumer());

        //
        rabbitConsumerSpy.onMessage(message);

        Mockito.verify(rabbitConsumerSpy, Mockito.times(1)).onMessage(message);
        Mockito.verify(rabbitConsumerSpy, Mockito.times(1)).getData(message);
        Mockito.verify(rabbitConsumerSpy, Mockito.times(1)).sendData(anyString());


    }
}