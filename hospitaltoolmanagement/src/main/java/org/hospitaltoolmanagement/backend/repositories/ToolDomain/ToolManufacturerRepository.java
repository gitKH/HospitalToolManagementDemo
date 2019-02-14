package org.hospitaltoolmanagement.backend.repositories.ToolDomain;

import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("toolManufacturerRepository")
public interface ToolManufacturerRepository extends JpaRepository<ToolManufacturerEntity, Integer> {
}
