package org.hospitaltoolmanagement.backend.utilities.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.hospitaltoolmanagement.backend.utilities.validation.ValidationMessages.RELATIONSHIP_FOUND_FOR_RECORD;

public class CommonValidator {

    public static List<ErrorDetail> validateBindingResult(BindingResult bindingResult) {
        Objects.requireNonNull(bindingResult);
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ErrorDetail> errorDetails = new ArrayList<>();
        allErrors.forEach(objectError ->
                errorDetails.add(new ErrorDetail(objectError.getDefaultMessage()))
        );
        return errorDetails;
    }

    public static ErrorDetail validateDependenciesRelation(Set<?> objectsSet, int id){
        if (objectsSet == null || objectsSet.isEmpty()){
            return null;
        } else {
            return new ErrorDetail(RELATIONSHIP_FOUND_FOR_RECORD + id);
        }
    }
}
