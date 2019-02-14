package org.hospitaltoolmanagement.backend.utilities.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {

    private String errorMessage;

    public ErrorDetail(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
