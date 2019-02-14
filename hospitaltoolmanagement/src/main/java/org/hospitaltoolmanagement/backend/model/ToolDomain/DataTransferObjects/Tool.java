package org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Tool {

    private int toolId;

    @Size(min = 3, message = TOOL_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = TOOL_NAME_CANNOT_BE_EMPTY)
    private String toolName;

    @Size(min = 3, message = TOOL_MODEL_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = TOOL_MODEL_CANNOT_BE_EMPTY)
    private String toolModel;

    @Size(min = 3, message = TOOL_SERIAL_NUMBER_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = TOOL_SERIAL_NUMBER_CANNOT_BE_EMPTY)
    private String toolSerialNumber;

    @NotNull(message = TOOL_YEAR_CANNOT_BE_EMPTY)
    private LocalDate toolYear;

    @NotNull(message = TOOL_MANUFACTURER_ID_CANNOT_BE_EMPTY)
    private int toolManufacturerId;

    @Size(min = 6, message = TOOL_MANUFACTURER_NAME_SHOULD_HAVE_AT_LEAST_6_CHARACTERS)
    @NotNull(message = TOOL_MANUFACTURER_NAME_CANNOT_BE_EMPTY)
    private String toolManufacturerName;

    @NotNull(message = TOOL_CATEGORY_ID_CANNOT_BE_EMPTY)
    private int toolCategoryId;

    @Size(min = 3, message = TOOL_CATEGORY_NAME_SHOULD_HAVE_AT_LEAST_3_CHARACTERS)
    @NotNull(message = TOOL_CATEGORY_NAME_CANNOT_BE_EMPTY)
    private String toolCategoryName;

    @NotNull(message = HOSPITAL_ROOM_ID_CANNOT_BE_EMPTY)
    private int hospitalRoomId;

    @Size(min = 5, message = HOSPITAL_ROOM_NAME_SHOULD_HAVE_AT_LEAST_5_CHARACTERS)
    @NotNull(message = HOSPITAL_ROOM_NAME_CANNOT_BE_EMPTY)
    private String hospitalRoomName;
}
