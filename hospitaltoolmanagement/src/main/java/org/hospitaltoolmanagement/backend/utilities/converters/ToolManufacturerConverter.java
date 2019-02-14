package org.hospitaltoolmanagement.backend.utilities.converters;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolManufacturer;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;

import java.util.LinkedHashSet;
import java.util.Set;

public class ToolManufacturerConverter {

    public static ToolManufacturerEntity convertToJpo(ToolManufacturer toolManufacturer) {
        ToolManufacturerEntity toolManufacturerEntity = new ToolManufacturerEntity();
        toolManufacturerEntity.setToolManufacturerId(toolManufacturer.getToolManufacturerId());
        toolManufacturerEntity.setToolManufacturerName(toolManufacturer.getToolManufacturerName());
        Set<Tool> toolSet = toolManufacturer.getToolSet();
        Set<ToolEntity> toolEntitiesSet = new LinkedHashSet<>();
        toolSet.forEach(tool ->
                toolEntitiesSet.add(ToolConverter.convertToJpo(tool))
        );
        toolManufacturerEntity.setToolEntitySet(toolEntitiesSet);
        return toolManufacturerEntity;
    }

    public static ToolManufacturer convertToDto(ToolManufacturerEntity toolManufacturerEntity) {
        ToolManufacturer toolManufacturer = new ToolManufacturer();
        toolManufacturer.setToolManufacturerId(toolManufacturerEntity.getToolManufacturerId());
        toolManufacturer.setToolManufacturerName(toolManufacturerEntity.getToolManufacturerName());
        Set<Tool> toolSet = new LinkedHashSet<>();
        toolManufacturerEntity.getToolEntitySet().forEach(toolEntity ->
                toolSet.add(ToolConverter.convertToDto(toolEntity))
        );
        toolManufacturer.setToolSet(toolSet);
        return toolManufacturer;
    }
}
