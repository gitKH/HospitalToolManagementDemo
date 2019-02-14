package org.hospitaltoolmanagement.backend.controllers;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalRoom;
import org.hospitaltoolmanagement.backend.services.api.HospitalRoomService;
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
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @RequestMapping(value = "/hospitalroom/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllHospitalRoomList() {
        try {
            List<HospitalRoom> hospitalRooms = hospitalRoomService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(hospitalRooms);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalroom/{hospitalRoomId}/getById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getSingleHospitalRoomById(@PathVariable(value = "hospitalRoomId") int hospitalRoomId) {
        try {
            HospitalRoom hospitalRoom = hospitalRoomService.findById(hospitalRoomId);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalRoom);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalroom/newHospitalRoom", method = RequestMethod.POST)
    public ResponseEntity<?> addOrUpdateHospitalRoom(@Valid @RequestBody HospitalRoom hospitalRoom, BindingResult bindingResult) {
        try {
            List<ErrorDetail> errorDetails = validateBindingResult(bindingResult);
            if (!errorDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            hospitalRoomService.createOrUpdateHospitalRoom(hospitalRoom);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalroom/{hospitalRoomId}/deleteById", method = RequestMethod.POST)
    public ResponseEntity<?> deleteHospitalRoom(@PathVariable(value = "hospitalRoomId") int hospitalRoomId) {
        try {
            HospitalRoom hospitalRoom = hospitalRoomService.findById(hospitalRoomId);
            ErrorDetail errorDetail = validateDependenciesRelation(hospitalRoom.getToolSet(), hospitalRoomId);
            if (errorDetail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
            }

            hospitalRoomService.deleteHospitalRoom(hospitalRoomId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
