package org.hospitaltoolmanagement.backend.controllers;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.ToolManufacturer;
import org.hospitaltoolmanagement.backend.services.api.ToolManufacturerService;
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
public class ToolManufacturerController {

    @Autowired
    private ToolManufacturerService toolManufacturerService;

    @RequestMapping(value = "/toolmanufacturer/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllToolManufacturerList() {
        try {
            List<ToolManufacturer> toolManufacturers = toolManufacturerService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(toolManufacturers);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolmanufacturer/{toolManufacturerId}/getById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getSingleToolManufacturerById(@PathVariable(value = "toolManufacturerId") int toolManufacturerId) {
        try {
            ToolManufacturer toolManufacturer = toolManufacturerService.findById(toolManufacturerId);
            return ResponseEntity.status(HttpStatus.OK).body(toolManufacturer);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolmanufacturer/newToolManufacturer", method = RequestMethod.POST)
    public ResponseEntity<?> addOrUpdateToolManufacturer(@Valid @RequestBody ToolManufacturer toolManufacturer, BindingResult bindingResult) {
        try {
            List<ErrorDetail> errorDetails = validateBindingResult(bindingResult);
            if (!errorDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            toolManufacturerService.createOrUpdateToolManufacturer(toolManufacturer);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/toolmanufacturer/{toolManufacturerId}/deleteById", method = RequestMethod.POST)
    public ResponseEntity<?> deleteToolManufacturerById(@PathVariable(value = "toolManufacturerId") int toolManufacturerId) {
        try {
            ToolManufacturer toolManufacturer = toolManufacturerService.findById(toolManufacturerId);
            ErrorDetail errorDetail = validateDependenciesRelation(toolManufacturer.getToolSet(), toolManufacturerId);
            if (errorDetail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
            }

            toolManufacturerService.deleteToolManufacturer(toolManufacturerId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
