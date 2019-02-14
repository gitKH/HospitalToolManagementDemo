package org.hospitaltoolmanagement.backend.services.api;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;

import java.util.List;

public interface ToolService {
    List<Tool> findAll();
    Tool findById(Integer toolId);
    void createOrUpdateTool(Tool tool);
    void deleteTool(Integer toolId);
}
