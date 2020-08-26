package co.ceiba.makePaymentClaim.makePaymentClaimService.listener;

import co.ceiba.makePaymentClaim.makePaymentClaimService.domain.Claim;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ClaimCompletedListener implements MessageListener {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody().toString());
        Claim claim = getInformation(message);
        System.out.println(claim.toString());

    }

    public Claim getInformation(Message message) {
        Claim claim = null;
        try {
            claim = mapper.readValue(new String(message.getBody()), Claim.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return claim;
    }
}

