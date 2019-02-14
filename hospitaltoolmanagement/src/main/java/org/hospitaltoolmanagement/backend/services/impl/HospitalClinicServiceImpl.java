package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalClinic;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.hospitaltoolmanagement.backend.repositories.HospitalDomain.HospitalClinicRepository;
import org.hospitaltoolmanagement.backend.services.api.HospitalClinicService;
import org.hospitaltoolmanagement.backend.utilities.converters.HospitalClinicConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalClinicServiceImpl implements HospitalClinicService {

    @Autowired
    private HospitalClinicRepository hospitalClinicRepository;

    @Override
    @Transactional
    public List<HospitalClinic> findAll() {
        List<HospitalClinicEntity> hospitalClinicEntities = hospitalClinicRepository.findAll();
        List<HospitalClinic> hospitalClinics = new ArrayList<>();
        hospitalClinicEntities.forEach(hospitalClinicEntity ->
                hospitalClinics.add(HospitalClinicConverter.convertToDto(hospitalClinicEntity))
        );
        return hospitalClinics;
    }

    @Override
    @Transactional
    public HospitalClinic findById(Integer hospitalRoomId) {
        Optional<HospitalClinicEntity> optionalHospitalClinicEntity = hospitalClinicRepository.findById(hospitalRoomId);
        if (optionalHospitalClinicEntity.isPresent()) {
            HospitalClinicEntity hospitalClinicEntity = optionalHospitalClinicEntity.get();
            return HospitalClinicConverter.convertToDto(hospitalClinicEntity);
        }
        return new HospitalClinic();
    }

    @Override
    @Transactional
    public void createOrUpdateHospitalClinic(HospitalClinic hospitalClinic) {
        HospitalClinicConverter.convertToJpo(hospitalClinic);
        hospitalClinicRepository.save(HospitalClinicConverter.convertToJpo(hospitalClinic));
    }

    @Override
    @Transactional
    public void deleteHospitalClinic(Integer hospitalRoomId) {
        hospitalClinicRepository.deleteById(hospitalRoomId);
    }

    /**
     * This setter method should be used only by unit tests.
     * @param hospitalClinicRepository
     */
    protected void setHospitalClinicRepository(HospitalClinicRepository hospitalClinicRepository) {
        this.hospitalClinicRepository = hospitalClinicRepository;
    }
}
