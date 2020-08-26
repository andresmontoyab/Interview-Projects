package co.ceiba.calculateinsuranceservice.rabbit;

import co.ceiba.calculateinsuranceservice.dto.Insurance;
import co.ceiba.calculateinsuranceservice.dto.Property;
import co.ceiba.calculateinsuranceservice.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitConsumer implements MessageListener {

    ObjectMapper mapper = new ObjectMapper();

    public String getData(Message message) {


        //Definiendo DTOs
        Insurance insurance;
        User user;
        Property property;
        String jsonInsurance = null;
        try {

            // Recuperando valores de la cola
            property = mapper.readValue(new String(message.getBody()), Property.class);
            System.out.println(property.toString());
            // Calculando valor del seguro
            double monthValue = (property.getValue() * 0.05) / 12;
            double costInsurance = monthValue + (monthValue * 0.01);

            // Inicializando DTO
            user = new User();
            insurance = new Insurance("", user, property, costInsurance);

            // Construyendo Mensaje a encolar
            jsonInsurance = mapper.writeValueAsString(insurance);
            System.out.println(jsonInsurance);


            System.out.println(property.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonInsurance;
    }

    @Override
    public void onMessage(Message message) {
        String data = getData(message);
        sendData(data);
    }

    public boolean sendData(String data) {
        // Instancia publicadora de rabbit
        RabbitPublisher rabbitPublisher = new RabbitPublisher();
        // Encolando nuevo mensaje
        rabbitPublisher.publishMessageAsync("co.ceiba.insurance", "calculated", data);
        return true;
    }
}
