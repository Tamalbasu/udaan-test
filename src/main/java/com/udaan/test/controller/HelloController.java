package com.udaan.test.controller;


import com.udaan.test.model.CovidResult;
import com.udaan.test.model.CovidSymtoms;
import com.udaan.test.model.Login;
import com.udaan.test.model.ZoneDetails;
import com.udaan.test.service.CovidService;
import com.udaan.test.service.LoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "udaansec")
public class HelloController {

    @Autowired
    LoginService loginService;

    @Autowired
    CovidService covidService;


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody Login login) {
        Map<String, String> data = loginService.addUser(login, false);
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "/registerAdmin", method = RequestMethod.POST)
    public ResponseEntity<Object> RegisterAdmin(@RequestBody Login login) {

        Map<String, String> data = loginService.addUser(login, true);
        return ResponseEntity.ok(data);


    }

    @RequestMapping(value = "/selfAssessment", method = RequestMethod.POST)
    public ResponseEntity<Object> SymptomsCheck(@RequestBody CovidSymtoms covid) {

        Map<String, String> data = null;
        try {
            data = covidService.symptomsCheck(covid);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);


    }

    @RequestMapping(value = "/updateCovidResult", method = RequestMethod.POST)
    public ResponseEntity<Object> updateCovidResult(@RequestBody CovidResult covid) {

        Map<String, String> data = null;
        try {
            data = covidService.updateCovidResult(covid);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);


    }

    @RequestMapping(value = "/getZoneInfo", method = RequestMethod.GET)
    public ResponseEntity<Object> getZoneId(@RequestParam String pin) {

        ZoneDetails data = null;
        try {
            data = covidService.getZoneId(pin);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);


    }
}