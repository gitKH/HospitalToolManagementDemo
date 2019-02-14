package org.hospitaltoolmanagement.backend.repositories.HospitalDomain;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("hospitalClinicRepository")
public interface HospitalClinicRepository extends JpaRepository<HospitalClinicEntity, Integer> {
}
