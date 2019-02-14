package org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.HOSPITAL_CLINIC_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS;
import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.HOSPITAL_ROOM_NAME_CANNOT_BE_EMPTY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HospitalClinic {

    private int hospitalClinicId;

    @Size(min = 5, message = HOSPITAL_CLINIC_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = HOSPITAL_ROOM_NAME_CANNOT_BE_EMPTY)
    private String hospitalClinicName;

    private Set<HospitalRoom> hospitalRoomSet;
}
