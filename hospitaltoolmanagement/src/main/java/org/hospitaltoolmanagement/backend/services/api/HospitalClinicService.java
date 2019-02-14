package org.hospitaltoolmanagement.backend.services.api;


import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalClinic;

import java.util.List;

public interface HospitalClinicService {
    List<HospitalClinic> findAll();
    HospitalClinic findById(Integer hospitalRoomId);
    void createOrUpdateHospitalClinic(HospitalClinic hospitalRoom);
    void deleteHospitalClinic(Integer hospitalRoomId);
}
