package org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "HOSPITAL_CLINIC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalClinicEntity {

    // column names
    private static final String HOSPITAL_CLINIC_ID_COLUMN = "hospital_clinic_id";
    private static final String HOSPITAL_CLINIC_NAME_COLUMN = "hospital_clinic_name";

    // entity associated names
    private static final String HOSPITAL_CLINIC_ENTITY = "hospitalClinicEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HOSPITAL_CLINIC_ID_COLUMN)
    private int hospitalClinicId;

    @Column(name = HOSPITAL_CLINIC_NAME_COLUMN)
    private String hospitalClinicName;

    @OneToMany(mappedBy = HOSPITAL_CLINIC_ENTITY)
    private Set<HospitalRoomEntity> hospitalRoomEntitySet;
}
