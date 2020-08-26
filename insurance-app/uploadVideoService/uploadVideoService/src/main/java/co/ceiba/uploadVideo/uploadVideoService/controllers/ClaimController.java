package co.ceiba.uploadVideo.uploadVideoService.controllers;

import co.ceiba.uploadVideo.uploadVideoService.dto.ClaimDTO;
import co.ceiba.uploadVideo.uploadVideoService.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
public class ClaimController {

    ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<ClaimDTO> createNewCustomer(@RequestBody ClaimDTO claimDTO) {
        return new ResponseEntity<ClaimDTO>(claimService.validateVideo(claimDTO), HttpStatus.CREATED);
    }
}
