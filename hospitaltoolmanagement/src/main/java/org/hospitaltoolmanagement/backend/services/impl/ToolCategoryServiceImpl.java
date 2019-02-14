package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolCategory;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolCategoryRepository;
import org.hospitaltoolmanagement.backend.services.api.ToolCategoryService;
import org.hospitaltoolmanagement.backend.utilities.converters.ToolCategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToolCategoryServiceImpl implements ToolCategoryService {

    @Autowired
    private ToolCategoryRepository toolCategoryRepository;

    @Override
    @Transactional
    public List<ToolCategory> findAll() {
        List<ToolCategoryEntity> toolCategoryEntities = toolCategoryRepository.findAll();
        List<ToolCategory> toolCategories = new ArrayList<>();
        toolCategoryEntities.forEach(toolCategoryEntity ->
                toolCategories.add(ToolCategoryConverter.convertToDto(toolCategoryEntity))
        );
        return toolCategories;
    }

    @Override
    @Transactional
    public ToolCategory findById(Integer toolCategoryId) {
        Optional<ToolCategoryEntity> optionalToolCategoryEntity = toolCategoryRepository.findById(toolCategoryId);
        if (optionalToolCategoryEntity.isPresent()) {
            ToolCategoryEntity toolCategoryEntity = optionalToolCategoryEntity.get();
            return ToolCategoryConverter.convertToDto(toolCategoryEntity);
        }
        return new ToolCategory();
    }

    @Override
    @Transactional
    public void createOrUpdateToolCategory(ToolCategory toolCategory) { toolCategoryRepository.save(ToolCategoryConverter.convertToJpo(toolCategory)); }

    @Override
    @Transactional
    public void deleteToolCategory(Integer toolCategoryId) { toolCategoryRepository.deleteById(toolCategoryId); }

    /**
     * This setter method should be used only by unit tests.
     * @param toolCategoryRepository
     */
    protected void setToolCategoryRepository(ToolCategoryRepository toolCategoryRepository) {
        this.toolCategoryRepository = toolCategoryRepository;
    }
}
