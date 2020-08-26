package co.ceiba.uploadVideo.uploadVideoService.service;

import co.ceiba.uploadVideo.uploadVideoService.domain.Video;
import co.ceiba.uploadVideo.uploadVideoService.dto.VideoDTO;

public interface VideoService {

    VideoDTO validateVideo(VideoDTO video);

    void sendProductMessage(String id, String queue);

}
