package co.ceiba.guardarinsuranceservice.guardar_insurance_service.listener;

import co.ceiba.guardarinsuranceservice.guardar_insurance_service.dominio.Insurance;
import co.ceiba.guardarinsuranceservice.guardar_insurance_service.repositorios.InsuranceRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class InsuranceListener implements MessageListener {

    private InsuranceRepository insuranceRepository;

    ObjectMapper mapper = new ObjectMapper();

    public InsuranceListener(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public void onMessage(Message message) {
        Insurance insurance = getInformation(message);
        if (insurance != null ) {
            insuranceRepository.save(insurance);
        }
    }

    public Insurance getInformation(Message message) {
        Insurance insurance = null;
        try {
            insurance = mapper.readValue(new String(message.getBody()), Insurance.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insurance;
    }

}
