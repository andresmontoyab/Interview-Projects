package co.ceiba.uploadVideo.uploadVideoService.controllers;

import co.ceiba.uploadVideo.uploadVideoService.dto.VideoDTO;
import co.ceiba.uploadVideo.uploadVideoService.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDTO> createNewCustomer(@RequestBody VideoDTO videoDTO) {
        return new ResponseEntity<VideoDTO>(videoService.validateVideo(videoDTO), HttpStatus.CREATED);
    }
}
