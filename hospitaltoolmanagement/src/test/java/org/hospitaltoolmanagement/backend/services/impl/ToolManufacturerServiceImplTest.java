package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolManufacturer;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolManufacturerRepository;
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

public class ToolManufacturerServiceImplTest {

    private ToolManufacturerServiceImpl toolManufacturerService;

    @Mock
    private ToolManufacturerRepository toolManufacturerRepository;

    private List<ToolManufacturerEntity> toolManufacturerEntities;

    private static final Integer TEST_TOOL_MANUFACTURER_ID_1 = 1;
    private static final Integer TEST_TOOL_MANUFACTURER_ID_2 = 2;
    private static final String TEST_TOOL_MANUFACTURER_NAME_1 = "tool manufacturer name 1";
    private static final String TEST_TOOL_MANUFACTURER_NAME_2 = "tool manufacturer name 2";

    @Before
    public void setUp() {
        toolManufacturerEntities = getToolManufacturerEntities();
        toolManufacturerRepository = mock(ToolManufacturerRepository.class);
        toolManufacturerService = new ToolManufacturerServiceImpl();
        toolManufacturerService.setToolManufacturerRepository(toolManufacturerRepository);
    }

    @Test
    public void findAllToolCategory() {
        try {
            given(toolManufacturerRepository.findAll()).willReturn(toolManufacturerEntities);
            List<ToolManufacturer> toolManufacturers = toolManufacturerService.findAll();
            Assert.assertNotNull(toolManufacturers);
            Assert.assertEquals(2, toolManufacturers.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void findByIdToolManufacturer() {
        try {
            given(toolManufacturerRepository.findById(TEST_TOOL_MANUFACTURER_ID_1))
                    .willReturn(toolManufacturerEntities.stream()
                            .filter(toolManufacturer -> TEST_TOOL_MANUFACTURER_ID_1.equals(toolManufacturer.getToolManufacturerId())).findAny());
            ToolManufacturer toolManufacturer = toolManufacturerService.findById(1);
            Assert.assertNotNull(toolManufacturer);
            Assert.assertEquals(TEST_TOOL_MANUFACTURER_ID_1.intValue(), toolManufacturer.getToolManufacturerId());
            Assert.assertEquals(TEST_TOOL_MANUFACTURER_NAME_1, toolManufacturer.getToolManufacturerName());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void createOrUpdateToolManufacturer() {
        try {
            given(toolManufacturerRepository.save(new ToolManufacturerEntity())).willReturn(null);
            ToolManufacturer toolManufacturer = new ToolManufacturer(TEST_TOOL_MANUFACTURER_ID_1, TEST_TOOL_MANUFACTURER_NAME_1, new LinkedHashSet<>());
            toolManufacturerService.createOrUpdateToolManufacturer(toolManufacturer);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteToolManufacturer() {
        try {
            doNothing().when(toolManufacturerRepository).deleteById(TEST_TOOL_MANUFACTURER_ID_1);
            toolManufacturerService.deleteToolManufacturer(TEST_TOOL_MANUFACTURER_ID_1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private List<ToolManufacturerEntity> getToolManufacturerEntities() {
        List<ToolManufacturerEntity> toolManufacturerEntities = new ArrayList<>();

        ToolManufacturerEntity toolManufacturerEntity1 = new ToolManufacturerEntity();
        toolManufacturerEntity1.setToolManufacturerId(TEST_TOOL_MANUFACTURER_ID_1);
        toolManufacturerEntity1.setToolManufacturerName(TEST_TOOL_MANUFACTURER_NAME_1);
        toolManufacturerEntity1.setToolEntitySet(new LinkedHashSet<>());

        ToolManufacturerEntity toolManufacturerEntity2 = new ToolManufacturerEntity();
        toolManufacturerEntity2.setToolManufacturerId(TEST_TOOL_MANUFACTURER_ID_2);
        toolManufacturerEntity2.setToolManufacturerName(TEST_TOOL_MANUFACTURER_NAME_2);
        toolManufacturerEntity2.setToolEntitySet(new LinkedHashSet<>());

        toolManufacturerEntities.add(toolManufacturerEntity1);
        toolManufacturerEntities.add(toolManufacturerEntity2);

        return toolManufacturerEntities;
    }
}