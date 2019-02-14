package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalClinic;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.hospitaltoolmanagement.backend.repositories.HospitalDomain.HospitalClinicRepository;
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

public class HospitalClinicServiceImplTest {

    private HospitalClinicServiceImpl hospitalClinicService;

    @Mock
    private HospitalClinicRepository hospitalClinicRepository;

    private List<HospitalClinicEntity> hospitalClinicEntities;

    private static final Integer TEST_CLINIC_ID_1 = 1;
    private static final Integer TEST_CLINIC_ID_2 = 2;
    private static final String TEST_CLINIC_NAME_1 = "clinic name 1";
    private static final String TEST_CLINIC_NAME_2 = "clinic name 2";

    @Before
    public void setUp() {
        hospitalClinicEntities = getHospitalClinicEntities();
        hospitalClinicRepository = mock(HospitalClinicRepository.class);
        hospitalClinicService = new HospitalClinicServiceImpl();
        hospitalClinicService.setHospitalClinicRepository(hospitalClinicRepository);
    }

    @Test
    public void findAllClinic() {
        try {
            given(hospitalClinicRepository.findAll()).willReturn(hospitalClinicEntities);
            List<HospitalClinic> hospitalClinics = hospitalClinicService.findAll();
            Assert.assertNotNull(hospitalClinics);
            Assert.assertEquals(2, hospitalClinics.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void findByIdClinic() {
        try {
            given(hospitalClinicRepository.findById(TEST_CLINIC_ID_1))
                    .willReturn(hospitalClinicEntities.stream()
                            .filter(clinic -> TEST_CLINIC_ID_1.equals(clinic.getHospitalClinicId())).findAny());
            HospitalClinic hospitalClinic = hospitalClinicService.findById(1);
            Assert.assertNotNull(hospitalClinic);
            Assert.assertEquals(TEST_CLINIC_ID_1.intValue(), hospitalClinic.getHospitalClinicId());
            Assert.assertEquals(TEST_CLINIC_NAME_1, hospitalClinic.getHospitalClinicName());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void createOrUpdateHospitalClinic() {
        try {
            given(hospitalClinicRepository.save(new HospitalClinicEntity())).willReturn(null);
            HospitalClinic hospitalClinic = new HospitalClinic(TEST_CLINIC_ID_1, TEST_CLINIC_NAME_1, new LinkedHashSet<>());
            hospitalClinicService.createOrUpdateHospitalClinic(hospitalClinic);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteHospitalClinic() {
        try {
            doNothing().when(hospitalClinicRepository).deleteById(TEST_CLINIC_ID_1);
            hospitalClinicService.deleteHospitalClinic(TEST_CLINIC_ID_1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private static List<HospitalClinicEntity> getHospitalClinicEntities() {
        List<HospitalClinicEntity> hospitalClinicEntities = new ArrayList<>();
        HospitalClinicEntity hospitalClinicEntity1 = new HospitalClinicEntity();
        hospitalClinicEntity1.setHospitalClinicId(TEST_CLINIC_ID_1);
        hospitalClinicEntity1.setHospitalClinicName(TEST_CLINIC_NAME_1);
        hospitalClinicEntity1.setHospitalRoomEntitySet(new LinkedHashSet<>());

        HospitalClinicEntity hospitalClinicEntity2 = new HospitalClinicEntity();
        hospitalClinicEntity2.setHospitalClinicId(TEST_CLINIC_ID_2);
        hospitalClinicEntity2.setHospitalClinicName(TEST_CLINIC_NAME_2);
        hospitalClinicEntity2.setHospitalRoomEntitySet(new LinkedHashSet<>());

        hospitalClinicEntities.add(hospitalClinicEntity1);
        hospitalClinicEntities.add(hospitalClinicEntity2);

        return hospitalClinicEntities;
    }
}