package co.ceiba.guardarinsuranceservice.guardar_insurance_service.services;

import co.ceiba.guardarinsuranceservice.guardar_insurance_service.dominio.Insurance;
import co.ceiba.guardarinsuranceservice.guardar_insurance_service.repositorios.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceInsuranceImpl implements ServiceInsurance {

    @Autowired
    private final InsuranceRepository insuranceRepository;

    public ServiceInsuranceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public boolean savedInterface(Insurance insurance) {
        savedInterface(insurance);
        return true;
    }

}
