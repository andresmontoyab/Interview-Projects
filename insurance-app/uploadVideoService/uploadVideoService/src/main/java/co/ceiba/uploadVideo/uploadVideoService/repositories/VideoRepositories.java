package co.ceiba.uploadVideo.uploadVideoService.repositories;

import co.ceiba.uploadVideo.uploadVideoService.domain.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepositories extends CrudRepository<Video, String> {
}
