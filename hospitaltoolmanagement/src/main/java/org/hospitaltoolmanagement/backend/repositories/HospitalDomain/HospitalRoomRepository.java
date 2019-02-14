package org.hospitaltoolmanagement.backend.repositories.HospitalDomain;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("hospitalRoomRepository")
public interface HospitalRoomRepository extends JpaRepository<HospitalRoomEntity, Integer> {
}
