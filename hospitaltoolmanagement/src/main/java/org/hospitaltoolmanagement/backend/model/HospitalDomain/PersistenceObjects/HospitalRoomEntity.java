package org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "HOSPITAL_ROOM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRoomEntity {

    // column names
    private static final String HOSPITAL_ROOM_ID_COLUMN = "hospital_room_id";
    private static final String HOSPITAL_ROOM_NAME_COLUMN = "hospital_room_name";

    // join column names
    private static final String HOSPITAL_CLINIC_COLUMN = "hospital_clinic";

    // entity associated names
    private static final String TOOL_ENTITY = "hospitalRoomEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HOSPITAL_ROOM_ID_COLUMN)
    private int hospitalRoomId;

    @Column(name = HOSPITAL_ROOM_NAME_COLUMN)
    private String hospitalRoomName;

    @ManyToOne
    @JoinColumn(name = HOSPITAL_CLINIC_COLUMN)
    private HospitalClinicEntity hospitalClinicEntity;
}
