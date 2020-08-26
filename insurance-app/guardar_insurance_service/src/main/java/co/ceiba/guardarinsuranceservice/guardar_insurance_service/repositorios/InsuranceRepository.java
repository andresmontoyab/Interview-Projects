package co.ceiba.guardarinsuranceservice.guardar_insurance_service.repositorios;

import co.ceiba.guardarinsuranceservice.guardar_insurance_service.dominio.Insurance;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<Insurance, String> {
}
