package org.hospitaltoolmanagement.backend.services.impl;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolManufacturer;
import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;
import org.hospitaltoolmanagement.backend.repositories.ToolDomain.ToolManufacturerRepository;
import org.hospitaltoolmanagement.backend.services.api.ToolManufacturerService;
import org.hospitaltoolmanagement.backend.utilities.converters.ToolManufacturerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToolManufacturerServiceImpl implements ToolManufacturerService {

    @Autowired
    private ToolManufacturerRepository toolManufacturerRepository;

    @Override
    @Transactional
    public List<ToolManufacturer> findAll() {
        List<ToolManufacturerEntity> toolManufacturerEntities = toolManufacturerRepository.findAll();
        List<ToolManufacturer> toolManufacturers = new ArrayList<>();
        toolManufacturerEntities.forEach(toolManufacturerEntity ->
                toolManufacturers.add(ToolManufacturerConverter.convertToDto(toolManufacturerEntity))
        );
        return toolManufacturers;
    }

    @Override
    @Transactional
    public ToolManufacturer findById(Integer toolManufacturerId) {
        Optional<ToolManufacturerEntity> optionalToolManufacturerEntity = toolManufacturerRepository.findById(toolManufacturerId);
        if (optionalToolManufacturerEntity.isPresent()) {
            ToolManufacturerEntity toolManufacturerEntity = optionalToolManufacturerEntity.get();
            return ToolManufacturerConverter.convertToDto(toolManufacturerEntity);
        }
        return new ToolManufacturer();
    }

    @Override
    @Transactional
    public void createOrUpdateToolManufacturer(ToolManufacturer toolManufacturer) {
        toolManufacturerRepository.save(ToolManufacturerConverter.convertToJpo(toolManufacturer));
    }

    @Override
    @Transactional
    public void deleteToolManufacturer(Integer toolManufacturerId) {
        toolManufacturerRepository.deleteById(toolManufacturerId);
    }

    /**
     * This setter method should be used only by unit tests.
     *
     * @param toolManufacturerRepository
     */
    protected void setToolManufacturerRepository(ToolManufacturerRepository toolManufacturerRepository) {
        this.toolManufacturerRepository = toolManufacturerRepository;
    }
}
