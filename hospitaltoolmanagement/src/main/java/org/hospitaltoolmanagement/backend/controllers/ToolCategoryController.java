package org.hospitaltoolmanagement.backend.controllers;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolCategory;
import org.hospitaltoolmanagement.backend.services.api.ToolCategoryService;
import org.hospitaltoolmanagement.backend.utilities.validation.ErrorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.hospitaltoolmanagement.backend.utilities.validation.CommonValidator.validateBindingResult;
import static org.hospitaltoolmanagement.backend.utilities.validation.CommonValidator.validateDependenciesRelation;

@RestController
@CrossOrigin(origins = "*")
public class ToolCategoryController {

    @Autowired
    private ToolCategoryService toolCategoryService;

    @RequestMapping(value = "/toolcategory/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllToolCategoryList() {
        try {
            List<ToolCategory> toolCategories = toolCategoryService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(toolCategories);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolcategory/{toolCategoryId}/getById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getSingleToolCategoryById(@PathVariable(value = "toolCategoryId") int toolCategoryId) {
        try {
            ToolCategory toolCategory = toolCategoryService.findById(toolCategoryId);
            return ResponseEntity.status(HttpStatus.OK).body(toolCategory);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolcategory/newToolCategory", method = RequestMethod.POST)
    public ResponseEntity<?> addOrUpdateToolCategory(@Valid @RequestBody ToolCategory toolCategory, BindingResult bindingResult) {
        try {
            List<ErrorDetail> errorDetails = validateBindingResult(bindingResult);
            if (!errorDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            toolCategoryService.createOrUpdateToolCategory(toolCategory);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolcategory/{toolCategoryId}/deleteById", method = RequestMethod.POST)
    public ResponseEntity<?> deleteToolCategoryById(@PathVariable(value = "toolCategoryId") int toolCategoryId) {
        try {
            ToolCategory toolCategory = toolCategoryService.findById(toolCategoryId);
            ErrorDetail errorDetail = validateDependenciesRelation(toolCategory.getToolSet(), toolCategoryId);
            if (errorDetail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
            }

            toolCategoryService.deleteToolCategory(toolCategoryId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
