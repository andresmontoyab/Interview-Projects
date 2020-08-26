package co.ceiba.uploadVideo.uploadVideoService.repositories;

import co.ceiba.uploadVideo.uploadVideoService.domain.Claim;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepositories extends CrudRepository<Claim, String> {
}
