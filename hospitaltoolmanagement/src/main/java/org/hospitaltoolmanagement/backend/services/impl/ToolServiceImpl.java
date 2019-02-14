package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolRepository;
import org.hospitaltoolmanagement.backend.services.api.ToolService;
import org.hospitaltoolmanagement.backend.utilities.converters.ToolConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    @Transactional
    public List<Tool> findAll() {
        List<ToolEntity> toolEntities = toolRepository.findAll();
        List<Tool> tools = new ArrayList<>();
        toolEntities.forEach(toolEntity ->
                tools.add(ToolConverter.convertToDto(toolEntity))
        );
        return tools;
    }

    @Override
    @Transactional
    public Tool findById(Integer toolId) {
        Optional<ToolEntity> optionalToolEntity = toolRepository.findById(toolId);
        if (optionalToolEntity.isPresent()) {
            ToolEntity toolEntity = optionalToolEntity.get();
            return ToolConverter.convertToDto(toolEntity);
        }
        return new Tool();
    }

    @Override
    @Transactional
    public void createOrUpdateTool(Tool tool) {
        toolRepository.save(ToolConverter.convertToJpo(tool));
    }

    @Override
    @Transactional
    public void deleteTool(Integer toolId) {
        toolRepository.deleteById(toolId);
    }

    /**
     * This setter method should be used only by unit tests.
     *
     * @param toolRepository
     */
    protected void setToolRepository(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }
}
