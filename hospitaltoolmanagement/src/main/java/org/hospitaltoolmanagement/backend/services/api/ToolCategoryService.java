package org.hospitaltoolmanagement.backend.services.api;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolCategory;

import java.util.List;

public interface ToolCategoryService {
    List<ToolCategory> findAll();
    ToolCategory findById(Integer toolCategoryId);
    void createOrUpdateToolCategory(ToolCategory toolCategory);
    void deleteToolCategory(Integer toolCategoryId);
}
