package org.hospitaltoolmanagement.backend.utilities.converters;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolCategory;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;

import java.util.LinkedHashSet;
import java.util.Set;

public class ToolCategoryConverter {

    public static ToolCategoryEntity convertToJpo(ToolCategory toolCategory) {
        ToolCategoryEntity toolCategoryEntity = new ToolCategoryEntity();
        toolCategoryEntity.setToolCategoryId(toolCategory.getToolCategoryId());
        toolCategoryEntity.setToolCategoryName(toolCategory.getToolCategoryName());
        Set<Tool> toolSet = toolCategory.getToolSet();
        Set<ToolEntity> toolEntitiesSet = new LinkedHashSet<>();
        toolSet.forEach(tool ->
                toolEntitiesSet.add(ToolConverter.convertToJpo(tool))
        );
        toolCategoryEntity.setToolEntitySet(toolEntitiesSet);
        return toolCategoryEntity;
    }

    public static ToolCategory convertToDto(ToolCategoryEntity toolCategoryEntity) {
        ToolCategory toolCategory = new ToolCategory();
        toolCategory.setToolCategoryId(toolCategoryEntity.getToolCategoryId());
        toolCategory.setToolCategoryName(toolCategoryEntity.getToolCategoryName());
        Set<Tool> toolSet= new LinkedHashSet<>();
        toolCategoryEntity.getToolEntitySet().forEach(toolEntity ->
                toolSet.add(ToolConverter.convertToDto(toolEntity))
        );
        toolCategory.setToolSet(toolSet);
        return toolCategory;
    }
}
