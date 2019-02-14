package org.hospitaltoolmanagement.backend.utilities.converters;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;

import java.time.ZoneId;
import java.util.Date;

public class ToolConverter {

    public static ToolEntity convertToJpo(Tool tool) {
        ToolEntity toolEntity = new ToolEntity();
        toolEntity.setToolId(tool.getToolId());
        toolEntity.setToolName(tool.getToolName());
        toolEntity.setToolModel(tool.getToolModel());
        toolEntity.setToolSerialNumber(tool.getToolSerialNumber());
        toolEntity.setToolYear(Date.from(tool.getToolYear().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ToolManufacturerEntity toolManufacturerEntity = new ToolManufacturerEntity(tool.getToolManufacturerId(), tool.getToolManufacturerName(), null);
        ToolCategoryEntity toolCategoryEntity = new ToolCategoryEntity(tool.getToolCategoryId(), tool.getToolCategoryName(), null);
        //TODO: Maybe need clinic entity
        HospitalRoomEntity hospitalRoomEntity = new HospitalRoomEntity(tool.getToolId(), tool.getHospitalRoomName(), null);
        toolEntity.setToolManufacturerEntity(toolManufacturerEntity);
        toolEntity.setToolCategoryEntity(toolCategoryEntity);
        toolEntity.setHospitalRoomEntity(hospitalRoomEntity);
        return toolEntity;
    }

    public static Tool convertToDto(ToolEntity toolEntity) {
        Tool tool = new Tool();
        tool.setToolId(toolEntity.getToolId());
        tool.setToolName(toolEntity.getToolName());
        tool.setToolModel(toolEntity.getToolModel());
        tool.setToolSerialNumber(toolEntity.getToolSerialNumber());
        tool.setToolYear(toolEntity.getToolYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        tool.setToolManufacturerId(toolEntity.getToolManufacturerEntity().getToolManufacturerId());
        tool.setToolManufacturerName(toolEntity.getToolManufacturerEntity().getToolManufacturerName());
        tool.setToolCategoryId(toolEntity.getToolCategoryEntity().getToolCategoryId());
        tool.setToolCategoryName(toolEntity.getToolCategoryEntity().getToolCategoryName());
        tool.setHospitalRoomId(toolEntity.getHospitalRoomEntity().getHospitalRoomId());
        tool.setHospitalRoomName(toolEntity.getHospitalRoomEntity().getHospitalRoomName());
        return tool;
    }
}
