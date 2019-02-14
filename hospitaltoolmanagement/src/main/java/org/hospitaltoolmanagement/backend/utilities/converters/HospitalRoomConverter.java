package org.hospitaltoolmanagement.backend.utilities.converters;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;

public class HospitalRoomConverter {

    public static HospitalRoomEntity convertToJpo(HospitalRoom hospitalRoom) {
        HospitalRoomEntity hospitalRoomEntity = new HospitalRoomEntity();
        hospitalRoomEntity.setHospitalRoomId(hospitalRoom.getHospitalRoomId());
        hospitalRoomEntity.setHospitalRoomName(hospitalRoom.getHospitalRoomName());
        HospitalClinicEntity hospitalClinicEntity = new HospitalClinicEntity(hospitalRoom.getHospitalClinicId(), hospitalRoom.getHospitalClinicName(), null);
        hospitalRoomEntity.setHospitalClinicEntity(hospitalClinicEntity);
        return hospitalRoomEntity;
    }

    public static HospitalRoom convertToDto(HospitalRoomEntity hospitalRoomEntity) {
        HospitalRoom hospitalRoom = new HospitalRoom();
        hospitalRoom.setHospitalRoomId(hospitalRoomEntity.getHospitalRoomId());
        hospitalRoom.setHospitalRoomName(hospitalRoomEntity.getHospitalRoomName());
        hospitalRoom.setHospitalClinicId(hospitalRoomEntity.getHospitalClinicEntity().getHospitalClinicId());
        hospitalRoom.setHospitalClinicName(hospitalRoomEntity.getHospitalClinicEntity().getHospitalClinicName());
        return hospitalRoom;
    }
}
