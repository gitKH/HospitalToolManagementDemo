package org.hospitaltoolmanagement.backend.repositories.ToolDomain;

import org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects.ToolCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("toolCategoryRepository")
public interface ToolCategoryRepository extends JpaRepository<ToolCategoryEntity, Integer> {
}
