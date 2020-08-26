package co.ceiba.guardarinsuranceservice.guardar_insurance_service.listener;

import co.ceiba.guardarinsuranceservice.guardar_insurance_service.dominio.Insurance;
import co.ceiba.guardarinsuranceservice.guardar_insurance_service.repositorios.InsuranceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class InsuranceListenerTest {

    ObjectMapper objectMapper;
    Message message;
    InsuranceListener insuranceListener;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Before
    public void setUp() throws Exception {
        message = new Message(("{\"id\":\"741259685312\", \"user\":{\"id\":\"5282\", \"name\":\"Carlos\", \"email\":\"carlos@carlos\", \"cellphone\":\"456789\"}, \"property\":{\"address\":\"Diag 55\", \"stratum\":3, \"value\":100000, \"type\":\"HOUSE\"}, \"costInsurance\":25000.0}".getBytes()), new MessageProperties());
        objectMapper = new ObjectMapper();
        insuranceListener = new InsuranceListener(insuranceRepository);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInformation(){
        //given
        Insurance insuranceExpected = null;
        try {
            insuranceExpected = objectMapper.readValue(new String(message.getBody()), Insurance.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //when
        Insurance insuranceReturn = insuranceListener.getInformation(message);

        //then
        assertEquals(insuranceExpected.getId(), insuranceReturn.getId());
        assertEquals(insuranceExpected.getCostInsurance(), insuranceReturn.getCostInsurance(), 0.1);

    }

}