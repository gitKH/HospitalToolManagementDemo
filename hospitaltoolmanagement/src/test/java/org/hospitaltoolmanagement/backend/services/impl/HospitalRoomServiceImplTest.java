package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;
import org.hospitaltoolmanagement.backend.repositories.HospitalDomain.HospitalRoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class HospitalRoomServiceImplTest {

    private HospitalRoomServiceImpl hospitalRoomService;

    @Mock
    private HospitalRoomRepository hospitalRoomRepository;

    private List<HospitalRoomEntity> hospitalRoomEntities;

    private static final Integer TEST_ROOM_ID_1 = 1;
    private static final Integer TEST_ROOM_ID_2 = 2;
    private static final String TEST_ROOM_NAME_1 = "room name 1";
    private static final String TEST_ROOM_NAME_2 = "room name 2";

    @Before
    public void setUp() {
        hospitalRoomEntities = getHospitalRoomEntities();
        hospitalRoomRepository = mock(HospitalRoomRepository.class);
        hospitalRoomService = new HospitalRoomServiceImpl();
        hospitalRoomService.setHospitalRoomRepository(hospitalRoomRepository);
    }

    @Test
    public void findAll() {
        try {
            given(hospitalRoomRepository.findAll()).willReturn(hospitalRoomEntities);
            List<HospitalRoom> hospitalRooms = hospitalRoomService.findAll();
            Assert.assertNotNull(hospitalRooms);
            Assert.assertEquals(2, hospitalRooms.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void findById() {
        try {
            given(hospitalRoomRepository.findById(TEST_ROOM_ID_1))
                    .willReturn(hospitalRoomEntities.stream()
                            .filter(room -> TEST_ROOM_ID_1.equals(room.getHospitalRoomId())).findAny());
            HospitalRoom hospitalRoom = hospitalRoomService.findById(TEST_ROOM_ID_1);
            Assert.assertNotNull(hospitalRoom);
            Assert.assertEquals(TEST_ROOM_ID_1.intValue(), hospitalRoom.getHospitalRoomId());
            Assert.assertEquals(TEST_ROOM_NAME_1, hospitalRoom.getHospitalRoomName());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void createOrUpdateHospitalRoom() {
        try {
            given(hospitalRoomRepository.save(new HospitalRoomEntity())).willReturn(null);
            HospitalRoom hospitalRoom = new HospitalRoom(TEST_ROOM_ID_1, TEST_ROOM_NAME_1, -1, null, new LinkedHashSet<>());
            hospitalRoomService.createOrUpdateHospitalRoom(hospitalRoom);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteHospitalRoom() {
        try {
            doNothing().when(hospitalRoomRepository).deleteById(TEST_ROOM_ID_1);
            hospitalRoomService.deleteHospitalRoom(TEST_ROOM_ID_1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private static List<HospitalRoomEntity> getHospitalRoomEntities() {
        List<HospitalRoomEntity> hospitalRoomEntities = new ArrayList<>();
        HospitalRoomEntity hospitalRoomEntity1 = new HospitalRoomEntity();
        hospitalRoomEntity1.setHospitalRoomId(TEST_ROOM_ID_1);
        hospitalRoomEntity1.setHospitalRoomName(TEST_ROOM_NAME_1);
        hospitalRoomEntity1.setHospitalClinicEntity(new HospitalClinicEntity());

        HospitalRoomEntity hospitalRoomEntity2 = new HospitalRoomEntity();
        hospitalRoomEntity2.setHospitalRoomId(TEST_ROOM_ID_2);
        hospitalRoomEntity2.setHospitalRoomName(TEST_ROOM_NAME_2);
        hospitalRoomEntity2.setHospitalClinicEntity(new HospitalClinicEntity());

        hospitalRoomEntities.add(hospitalRoomEntity1);
        hospitalRoomEntities.add(hospitalRoomEntity2);

        return hospitalRoomEntities;
    }
}