package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolCategory;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolCategoryRepository;
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

public class ToolCategoryServiceImplTest {

    private ToolCategoryServiceImpl toolCategoryService;

    @Mock
    private ToolCategoryRepository toolCategoryRepository;

    private List<ToolCategoryEntity> toolCategoryEntities;

    private static final Integer TEST_TOOL_CATEGORY_ID_1 = 1;
    private static final Integer TEST_TOOL_CATEGORY_ID_2 = 2;
    private static final String TEST_TOOL_CATEGORY_NAME_1 = "tool category name 1";
    private static final String TEST_TOOL_CATEGORY_NAME_2 = "tool category name 2";

    @Before
    public void setUp() {
        toolCategoryEntities = getToolCategoryEntities();
        toolCategoryRepository = mock(ToolCategoryRepository.class);
        toolCategoryService = new ToolCategoryServiceImpl();
        toolCategoryService.setToolCategoryRepository(toolCategoryRepository);
    }

    @Test
    public void findAllToolCategory() {
        try {
            given(toolCategoryRepository.findAll()).willReturn(toolCategoryEntities);
            List<ToolCategory> toolCategories = toolCategoryService.findAll();
            Assert.assertNotNull(toolCategories);
            Assert.assertEquals(2, toolCategories.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void findByIdToolCategory() {
        try {
            given(toolCategoryRepository.findById(TEST_TOOL_CATEGORY_ID_1))
                    .willReturn(toolCategoryEntities.stream()
                            .filter(toolCategory -> TEST_TOOL_CATEGORY_ID_1.equals(toolCategory.getToolCategoryId())).findAny());
            ToolCategory toolCategory = toolCategoryService.findById(1);
            Assert.assertNotNull(toolCategory);
            Assert.assertEquals(TEST_TOOL_CATEGORY_ID_1.intValue(), toolCategory.getToolCategoryId());
            Assert.assertEquals(TEST_TOOL_CATEGORY_NAME_1, toolCategory.getToolCategoryName());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void createOrUpdateToolCategory() {
        try {
            given(toolCategoryRepository.save(new ToolCategoryEntity())).willReturn(null);
            ToolCategory toolCategory = new ToolCategory(TEST_TOOL_CATEGORY_ID_1, TEST_TOOL_CATEGORY_NAME_1, new LinkedHashSet<>());
            toolCategoryService.createOrUpdateToolCategory(toolCategory);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteToolCategory() {
        try {
            doNothing().when(toolCategoryRepository).deleteById(TEST_TOOL_CATEGORY_ID_1);
            toolCategoryService.deleteToolCategory(TEST_TOOL_CATEGORY_ID_1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private List<ToolCategoryEntity> getToolCategoryEntities() {
        List<ToolCategoryEntity> toolCategoryEntities = new ArrayList<>();

        ToolCategoryEntity toolCategoryEntity1 = new ToolCategoryEntity();
        toolCategoryEntity1.setToolCategoryId(TEST_TOOL_CATEGORY_ID_1);
        toolCategoryEntity1.setToolCategoryName(TEST_TOOL_CATEGORY_NAME_1);
        toolCategoryEntity1.setToolEntitySet(new LinkedHashSet<>());

        ToolCategoryEntity toolCategoryEntity2 = new ToolCategoryEntity();
        toolCategoryEntity2.setToolCategoryId(TEST_TOOL_CATEGORY_ID_2);
        toolCategoryEntity2.setToolCategoryName(TEST_TOOL_CATEGORY_NAME_2);
        toolCategoryEntity2.setToolEntitySet(new LinkedHashSet<>());

        toolCategoryEntities.add(toolCategoryEntity1);
        toolCategoryEntities.add(toolCategoryEntity2);

        return toolCategoryEntities;
    }
}