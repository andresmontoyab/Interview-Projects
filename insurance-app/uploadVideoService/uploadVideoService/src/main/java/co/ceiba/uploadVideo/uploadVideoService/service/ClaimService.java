package co.ceiba.uploadVideo.uploadVideoService.service;

import co.ceiba.uploadVideo.uploadVideoService.dto.ClaimDTO;

public interface ClaimService {

    ClaimDTO validateVideo(ClaimDTO video);

    void sendProductMessage(String id, String queue);

}
