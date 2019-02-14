package org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects;

import lombok.*;
import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HospitalRoom {

    private int hospitalRoomId;

    @Size(min = 5, message = HOSPITAL_ROOM_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = HOSPITAL_ROOM_NAME_CANNOT_BE_EMPTY)
    private String hospitalRoomName;

    @NotNull(message = HOSPITAL_CLINIC_ID_CANNOT_BE_EMPTY)
    @Positive(message = HOSPITAL_CLINIC_ID_SHOULD_BE_POSITIVE)
    private int hospitalClinicId;

    @Size(min = 5, message = HOSPITAL_CLINIC_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = HOSPITAL_CLINIC_NAME_CANNOT_BE_EMPTY)
    private String hospitalClinicName;

    private Set<Tool> toolSet;
}
