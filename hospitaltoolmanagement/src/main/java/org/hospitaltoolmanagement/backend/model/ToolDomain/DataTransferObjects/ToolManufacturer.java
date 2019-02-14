package org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.TOOL_MANUFACTURER_NAME_CANNOT_BE_EMPTY;
import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.TOOL_MANUFACTURER_NAME_SHOULD_HAVE_AT_LEAST_6_CHARACTERS;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ToolManufacturer {

    private int toolManufacturerId;

    @Size(min = 6, message = TOOL_MANUFACTURER_NAME_SHOULD_HAVE_AT_LEAST_6_CHARACTERS)
    @NotNull(message = TOOL_MANUFACTURER_NAME_CANNOT_BE_EMPTY)
    private String toolManufacturerName;
    private Set<Tool> toolSet;
}
