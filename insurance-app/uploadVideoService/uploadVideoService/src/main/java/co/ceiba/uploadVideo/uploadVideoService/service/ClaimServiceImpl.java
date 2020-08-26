package co.ceiba.uploadVideo.uploadVideoService.service;

import co.ceiba.uploadVideo.uploadVideoService.domain.Claim;
import co.ceiba.uploadVideo.uploadVideoService.dto.ClaimDTO;
import co.ceiba.uploadVideo.uploadVideoService.repositories.ClaimRepositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {


   private RabbitTemplate rabbitTemplate;
   private final ClaimRepositories claimRepositories;
   private ObjectMapper objectMapper = new ObjectMapper();

    public ClaimServiceImpl(ClaimRepositories claimRepositories, RabbitTemplate rabbitTemplate) {
        this.claimRepositories = claimRepositories;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public ClaimDTO validateVideo(ClaimDTO claim) {
       if(claim.getName().length() > 10 ){
           claim.setStatus("Ok");
           Claim claimDomain = claimDTOToClaim(claim);

           String messageToSend = null;
           try {
               messageToSend = objectMapper.writeValueAsString(claim);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
           sendProductMessage(messageToSend, "co.ceiba.claimCompleted");
           return claimToClaimDTO(claimRepositories.save(claimDomain));
       } else {
           claim.setStatus("Ok");

           String messageToSend = null;
           try {
               messageToSend = objectMapper.writeValueAsString(claim);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
           sendProductMessage(messageToSend, "co.ceiba.claimCompleted");
           return claim;
       }
    }

    public void sendProductMessage(String message, String queue) {
        System.out.println(("Sending the index request through queue message"));
        rabbitTemplate.send(queue, new Message(message.getBytes(), new MessageProperties()));
    }



    private Claim claimDTOToClaim(ClaimDTO claimDTO){
        Claim claim = new Claim();
        if (claimDTO != null) {
            claim.setIdInsurance(claimDTO.getIdInsurance());
            claim.setStatus(claimDTO.getStatus());
            claim.setId(claimDTO.getId());
            claim.setName(claimDTO.getName());
            claim.setDuration(claimDTO.getDuration());
        }
        return claim;
    }

    private ClaimDTO claimToClaimDTO(Claim claim){
        ClaimDTO claimDTO = new ClaimDTO();
        if (claim != null) {
            claimDTO.setIdInsurance(claim.getIdInsurance());
            claimDTO.setStatus(claim.getStatus());
            claimDTO.setId(claim.getId());
            claimDTO.setName(claim.getName());
            claimDTO.setDuration(claim.getDuration());
        }
        return claimDTO;
    }
}
