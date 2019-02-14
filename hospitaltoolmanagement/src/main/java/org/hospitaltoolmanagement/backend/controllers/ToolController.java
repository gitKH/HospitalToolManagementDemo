package org.hospitaltoolmanagement.backend.controllers;

import org.hospitaltoolmanagement.backend.model.ToolDomain.DataTransferObjects.Tool;
import org.hospitaltoolmanagement.backend.services.api.ToolService;
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

@RestController
@CrossOrigin(origins = "*")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @RequestMapping(value = "/tool/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllToolList() {
        try {
            List<Tool> tools = toolService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(tools);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/tool/{toolId}/getById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getSingleToolById(@PathVariable(value = "toolId") int toolId) {
        try {
            Tool tool = toolService.findById(toolId);
            return ResponseEntity.status(HttpStatus.OK).body(tool);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/tool/newTool", method = RequestMethod.POST)
    public ResponseEntity<?> addOrUpdateTool(@Valid @RequestBody Tool tool, BindingResult bindingResult) {
        try {
            List<ErrorDetail> errorDetails = validateBindingResult(bindingResult);
            if (!errorDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            toolService.createOrUpdateTool(tool);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/tool/{toolId}/deleteById", method = RequestMethod.POST)
    public ResponseEntity<?> deleteToolById(@PathVariable(value = "toolId") int toolId) {
        try {
            toolService.deleteTool(toolId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
