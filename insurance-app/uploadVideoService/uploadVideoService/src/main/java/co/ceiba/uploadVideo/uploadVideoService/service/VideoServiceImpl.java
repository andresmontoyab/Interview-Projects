package co.ceiba.uploadVideo.uploadVideoService.service;

import co.ceiba.uploadVideo.uploadVideoService.UploadVideoServiceApplication;
import co.ceiba.uploadVideo.uploadVideoService.domain.Video;
import co.ceiba.uploadVideo.uploadVideoService.dto.VideoDTO;
import co.ceiba.uploadVideo.uploadVideoService.repositories.VideoRepositories;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService{

   private RabbitTemplate rabbitTemplate;
   private final VideoRepositories videoRepositories;

    public VideoServiceImpl(VideoRepositories videoRepositories, RabbitTemplate rabbitTemplate) {
        this.videoRepositories = videoRepositories;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public VideoDTO validateVideo(VideoDTO video) {
       if(video.getName().length() > 10 ){
           Video videoDomain = videoDTOToVideo(video);
           sendProductMessage(video.getId(), "co.ceiba.claimValidated");
           return videoToVideoDTO(videoRepositories.save(videoDomain));
       } else {
           sendProductMessage(video.getId(), "co.ceiba.claimNotValidated");
           return video;
       }
    }

    public void sendProductMessage(String id, String queue) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        System.out.println(("Sending the index request through queue message"));
        rabbitTemplate.convertAndSend(queue, actionmap);
    }



    private Video videoDTOToVideo(VideoDTO videoDTO){
        Video video = new Video();
        if (videoDTO != null) {
            video.setId(videoDTO.getId());
            video.setName(videoDTO.getName());
            video.setDuration(videoDTO.getDuration());
        }
        return video;
    }

    private VideoDTO videoToVideoDTO(Video video){
        VideoDTO videoDTO = new VideoDTO();
        if (video != null) {
            videoDTO.setId(video.getId());
            videoDTO.setName(video.getName());
            videoDTO.setDuration(video.getDuration());
        }
        return videoDTO;
    }
}
