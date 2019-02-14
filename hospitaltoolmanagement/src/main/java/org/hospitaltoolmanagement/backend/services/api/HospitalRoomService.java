package org.hospitaltoolmanagement.backend.services.api;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;

import java.util.List;

public interface HospitalRoomService {
    List<HospitalRoom> findAll();
    HospitalRoom findById(Integer hospitalRoomId);
    void createOrUpdateHospitalRoom(HospitalRoom hospitalRoom);
    void deleteHospitalRoom(Integer hospitalRoomId);
}
