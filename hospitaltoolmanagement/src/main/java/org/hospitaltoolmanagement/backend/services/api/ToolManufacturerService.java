package org.hospitaltoolmanagement.backend.services.api;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolManufacturer;

import java.util.List;

public interface ToolManufacturerService {
    List<ToolManufacturer> findAll();
    ToolManufacturer findById(Integer toolManufacturerId);
    void createOrUpdateToolManufacturer(ToolManufacturer toolManufacturer);
    void deleteToolManufacturer(Integer toolManufacturerId);
}
