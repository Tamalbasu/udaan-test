package com.udaan.test.service;

import com.udaan.test.model.CovidResult;
import com.udaan.test.model.CovidSymtoms;
import com.udaan.test.model.Login;
import com.udaan.test.model.ZoneDetails;

import java.util.Map;

public interface CovidService {

    public Map<String,String> symptomsCheck(CovidSymtoms covidSymtoms) throws Exception;

    public Map<String,String> updateCovidResult(CovidResult covidResult) throws Exception;
    public ZoneDetails getZoneId(String pin) throws Exception;

}
