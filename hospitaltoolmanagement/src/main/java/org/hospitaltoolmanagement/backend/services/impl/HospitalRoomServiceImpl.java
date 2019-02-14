package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;
import org.hospitaltoolmanagement.backend.repositories.HospitalDomain.HospitalRoomRepository;
import org.hospitaltoolmanagement.backend.services.api.HospitalRoomService;
import org.hospitaltoolmanagement.backend.utilities.converters.HospitalRoomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalRoomServiceImpl implements HospitalRoomService {

    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    @Override
    @Transactional
    public List<HospitalRoom> findAll() {
        List<HospitalRoomEntity> hospitalRoomEntities = hospitalRoomRepository.findAll();
        List<HospitalRoom> hospitalRooms = new ArrayList<>();
        hospitalRoomEntities.forEach(hospitalRoomEntity ->
                hospitalRooms.add(HospitalRoomConverter.convertToDto(hospitalRoomEntity))
        );
        return hospitalRooms;
    }

    @Override
    @Transactional
    public HospitalRoom findById(Integer hospitalRoomId) {
        Optional<HospitalRoomEntity> optionalHospitalRoomEntity = hospitalRoomRepository.findById(hospitalRoomId);
        if (optionalHospitalRoomEntity.isPresent()) {
            HospitalRoomEntity hospitalRoomEntity = optionalHospitalRoomEntity.get();
            return HospitalRoomConverter.convertToDto(hospitalRoomEntity);
        }
        return new HospitalRoom();
    }

    @Override
    @Transactional
    public void createOrUpdateHospitalRoom(HospitalRoom hospitalRoom) { hospitalRoomRepository.save(HospitalRoomConverter.convertToJpo(hospitalRoom)); }

    @Override
    @Transactional
    public void deleteHospitalRoom(Integer hospitalRoomId) { hospitalRoomRepository.deleteById(hospitalRoomId); }

    /**
     * This setter method should be used only by unit tests.
     * @param hospitalRoomRepository
     */
    protected void setHospitalRoomRepository(HospitalRoomRepository hospitalRoomRepository) {
        this.hospitalRoomRepository = hospitalRoomRepository;
    }
}
