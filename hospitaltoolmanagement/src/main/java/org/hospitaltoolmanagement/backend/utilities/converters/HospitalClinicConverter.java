package org.hospitaltoolmanagement.backend.utilities.converters;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalClinic;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;

import java.util.LinkedHashSet;
import java.util.Set;

public class HospitalClinicConverter {

    public static HospitalClinicEntity convertToJpo(HospitalClinic hospitalClinic) {
        HospitalClinicEntity hospitalClinicEntity = new HospitalClinicEntity();
        hospitalClinicEntity.setHospitalClinicId(hospitalClinic.getHospitalClinicId());
        hospitalClinicEntity.setHospitalClinicName(hospitalClinic.getHospitalClinicName());
        Set<HospitalRoom> hospitalRoomSet = hospitalClinic.getHospitalRoomSet();
        Set<HospitalRoomEntity> hospitalRoomEntities = new LinkedHashSet<>();
        if (hospitalRoomSet != null) {
            hospitalRoomSet.forEach(hospitalRoom ->
                    hospitalRoomEntities.add(HospitalRoomConverter.convertToJpo(hospitalRoom))
            );
        }
        hospitalClinicEntity.setHospitalRoomEntitySet(hospitalRoomEntities);
        return hospitalClinicEntity;
    }

    public static HospitalClinic convertToDto(HospitalClinicEntity hospitalClinicEntity) {
        HospitalClinic hospitalClinic = new HospitalClinic();
        hospitalClinic.setHospitalClinicId(hospitalClinicEntity.getHospitalClinicId());
        hospitalClinic.setHospitalClinicName(hospitalClinicEntity.getHospitalClinicName());
        Set<HospitalRoom> hospitalRoomsSet = new LinkedHashSet<>();
        hospitalClinicEntity.getHospitalRoomEntitySet().forEach(hospitalRoomEntity ->
                hospitalRoomsSet.add(HospitalRoomConverter.convertToDto(hospitalRoomEntity))
        );
        hospitalClinic.setHospitalRoomSet(hospitalRoomsSet);
        return hospitalClinic;
    }
}
