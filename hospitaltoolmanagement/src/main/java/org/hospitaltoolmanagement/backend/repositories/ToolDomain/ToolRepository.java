package org.hospitaltoolmanagement.backend.repositories.ToolDomain;

import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("toolRepository")
public interface ToolRepository extends JpaRepository<ToolEntity, Integer> {
}
