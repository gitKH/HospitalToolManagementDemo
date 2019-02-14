package org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.TOOL_CATEGORY_NAME_CANNOT_BE_EMPTY;
import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.TOOL_CATEGORY_NAME_SHOULD_HAVE_AT_LEAST_3_CHARACTERS;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ToolCategory {

    private int toolCategoryId;

    @Size(min = 3, message = TOOL_CATEGORY_NAME_SHOULD_HAVE_AT_LEAST_3_CHARACTERS)
    @NotNull(message = TOOL_CATEGORY_NAME_CANNOT_BE_EMPTY)
    private String toolCategoryName;

    private Set<Tool> toolSet;
}
