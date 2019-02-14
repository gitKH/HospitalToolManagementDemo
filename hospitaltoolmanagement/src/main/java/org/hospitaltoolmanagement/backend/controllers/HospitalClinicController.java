package org.hospitaltoolmanagement.backend.controllers;

import org.hospitaltoolmanagement.backend.model.HospitalDomain.DataTransferObjects.HospitalClinic;
import org.hospitaltoolmanagement.backend.services.api.HospitalClinicService;
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
public class HospitalClinicController {

    @Autowired
    private HospitalClinicService hospitalClinicController;

    @RequestMapping(value = "/hospitalclinic/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<HospitalClinic>> getAllHospitalClinicList() {
        try {
            List<HospitalClinic> hospitalClinics = hospitalClinicController.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(hospitalClinics);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalclinic/{hospitalClinicId}/getById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<HospitalClinic> getSingleHospitalClinicById(@PathVariable(value = "hospitalClinicId") int hospitalClinicId) {
        try {
            HospitalClinic hospitalClinic = hospitalClinicController.findById(hospitalClinicId);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalClinic);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalclinic/newHospitalClinic", method = RequestMethod.POST)
    public ResponseEntity<?> addOrUpdateHospitalClinic(@Valid @RequestBody HospitalClinic hospitalClinic, BindingResult bindingResult) {
        try {
            List<ErrorDetail> errorDetails = validateBindingResult(bindingResult);
            if (!validateBindingResult(bindingResult).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            } else {
                hospitalClinicController.createOrUpdateHospitalClinic(hospitalClinic);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/hospitalclinic/{hospitalClinicId}/deleteById", method = RequestMethod.POST)
    public ResponseEntity<?> deleteHospitalClinic(@PathVariable(value = "hospitalClinicId") int hospitalClinicId) {
        try {
            HospitalClinic hospitalClinic = hospitalClinicController.findById(hospitalClinicId);
            ErrorDetail errorDetail = validateDependenciesRelation(hospitalClinic.getHospitalRoomSet(), hospitalClinicId);
            if (errorDetail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
            }

            hospitalClinicController.deleteHospitalClinic(hospitalClinicId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
