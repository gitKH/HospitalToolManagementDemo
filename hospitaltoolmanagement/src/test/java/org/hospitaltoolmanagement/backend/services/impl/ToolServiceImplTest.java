package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class ToolServiceImplTest {

    private ToolServiceImpl toolService;

    @Mock
    private ToolRepository toolRepository;

    private List<ToolEntity> toolEntities;

    private static final Integer TEST_TOOL_ID_1 = 1;
    private static final Integer TEST_TOOL_ID_2 = 2;
    private static final String TEST_TOOL_NAME_1 = "tool name 1";
    private static final String TEST_TOOL_NAME_2 = "tool name 2";
    private static final String TEST_TOOL_MODEL_1 = "tool model 1";
    private static final String TEST_TOOL_MODEL_2 = "tool model 2";
    private static final String TEST_TOOL_SERIAL_1 = "tool serial 1";
    private static final String TEST_TOOL_SERIAL_2 = "tool serial 2";
    private static final Date TEST_TOOL_DATE = new Date();

    @Before
    public void setUp() {
        toolEntities = getToolEntities();
        toolRepository = mock(ToolRepository.class);
        toolService = new ToolServiceImpl();
        toolService.setToolRepository(toolRepository);
    }

    @Test
    public void findAllTool() {
        try {
            given(toolRepository.findAll()).willReturn(toolEntities);
            List<Tool> tools = toolService.findAll();
            Assert.assertNotNull(tools);
            Assert.assertEquals(2, tools.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void findByIdTool() {
        try {
            given(toolRepository.findById(TEST_TOOL_ID_1))
                    .willReturn(toolEntities.stream()
                            .filter(tool -> TEST_TOOL_ID_1.equals(tool.getToolId())).findAny());
            Tool tool = toolService.findById(TEST_TOOL_ID_1);
            Assert.assertNotNull(tool);
            Assert.assertEquals(TEST_TOOL_ID_1.intValue(), tool.getToolId());
            Assert.assertEquals(TEST_TOOL_NAME_1, tool.getToolName());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void createOrUpdateTool() {
        try {
            given(toolRepository.save(new ToolEntity())).willReturn(null);
            Tool tool = new Tool(TEST_TOOL_ID_1, TEST_TOOL_NAME_1, TEST_TOOL_MODEL_1, TEST_TOOL_SERIAL_1, LocalDate.now(),
                    0, "", 0, "", 0, "");
            toolService.createOrUpdateTool(tool);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteTool() {
        try {
            doNothing().when(toolRepository).deleteById(TEST_TOOL_ID_1);
            toolService.deleteTool(TEST_TOOL_ID_1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private List<ToolEntity> getToolEntities() {
        List<ToolEntity> toolEntities = new ArrayList<>();

        ToolEntity toolEntity1 = new ToolEntity();
        toolEntity1.setToolId(TEST_TOOL_ID_1);
        toolEntity1.setToolName(TEST_TOOL_NAME_1);
        toolEntity1.setToolModel(TEST_TOOL_MODEL_1);
        toolEntity1.setToolSerialNumber(TEST_TOOL_SERIAL_1);
        toolEntity1.setToolYear(TEST_TOOL_DATE);
        toolEntity1.setHospitalRoomEntity(new HospitalRoomEntity());
        toolEntity1.setToolCategoryEntity(new ToolCategoryEntity());
        toolEntity1.setToolManufacturerEntity(new ToolManufacturerEntity());

        ToolEntity toolEntity2 = new ToolEntity();
        toolEntity2.setToolId(TEST_TOOL_ID_2);
        toolEntity2.setToolName(TEST_TOOL_NAME_2);
        toolEntity2.setToolModel(TEST_TOOL_MODEL_2);
        toolEntity2.setToolSerialNumber(TEST_TOOL_SERIAL_2);
        toolEntity2.setToolYear(TEST_TOOL_DATE);
        toolEntity2.setHospitalRoomEntity(new HospitalRoomEntity());
        toolEntity2.setToolCategoryEntity(new ToolCategoryEntity());
        toolEntity2.setToolManufacturerEntity(new ToolManufacturerEntity());

        toolEntities.add(toolEntity1);
        toolEntities.add(toolEntity2);

        return toolEntities;
    }
}