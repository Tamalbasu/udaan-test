package com.udaan.test.service.impl;


import com.udaan.test.model.CovidResult;
import com.udaan.test.model.CovidSymtoms;
import com.udaan.test.model.Registration;
import com.udaan.test.model.ZoneDetails;
import com.udaan.test.repository.CovidRepository;
import com.udaan.test.repository.UserRepository;
import com.udaan.test.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CovidServiceImpl implements CovidService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CovidRepository covidRepository;
    @Override
    public Map<String, String> symptomsCheck(CovidSymtoms covidSymtoms) throws Exception {

        if(userRepository.findById(covidSymtoms.getUserId()).isEmpty()){
            throw new Exception("User not found");
        }
        List<String> data = covidSymtoms.getSymptoms();
        List<String> sypt = Arrays.asList("fever","cold","cough");
        if(data.size()>3){
            throw new Exception();
        }
        int count = 0;
       if(data.size()>=1){
           for (String dt:data
                ) {
               if(dt.equalsIgnoreCase("fever") ){
               count++;
               }
               if(dt.equalsIgnoreCase("cold") ){
                   count++;
               }
               if(dt.equalsIgnoreCase("cough") ){
                   count++;
               }
           }

       }
        Map<String, String> sym = new HashMap<>();
        if(covidSymtoms.isContact() || covidSymtoms.isTravelHistory() ){
            if(count>2){
                sym.put("riskPercentage","95");
            }
            if(count==2){

                sym.put("riskPercentage","75");
            }
            if(count==1){

                sym.put("riskPercentage","50");
            }
        }else{
            sym.put("riskPercentage","5");
        }

        return sym;
    }

    @Override
    public Map<String, String> updateCovidResult(CovidResult covidResult) throws Exception {
        if(userRepository.findById(Integer.valueOf(covidResult.getUserid())).isEmpty()){
            throw new Exception("User not found");
        }
        if(userRepository.findById(Integer.valueOf(covidResult.getAdminid())).isEmpty()){
            throw new Exception("User not found");
        }
        Registration registration = userRepository.findById(Integer.valueOf(covidResult.getAdminid())).get();
        if(registration.getAdmin().equals("0")){
            throw new Exception("User not found");
        }
        try {
            covidRepository.save(covidResult);
        }catch (Exception e){
            Map<String, String> sym = new HashMap<>();
            sym.put("Updated","false");
            return sym;
        }
        Map<String, String> sym = new HashMap<>();
        sym.put("Updated","true");
        return sym;
    }

    @Override
    public ZoneDetails getZoneId(String pin) throws Exception {
       List<Registration> registrations= userRepository.findByPincode(pin);
       if(registrations.isEmpty()){
           throw new Exception("pin not found");
       }
       int count=0;
        for (Registration registration:registrations
             ) {
            if(!covidRepository.findById(registration.getId()).isEmpty()){
                CovidResult covidResult = covidRepository.findById(registration.getId()).get();
                if(covidResult.getResult().equals("positive")){
                    count++;
                }
            }
        }

        ZoneDetails zoneDetails = new ZoneDetails();
        if(count>=5)
        {
            zoneDetails.setNumCases(String.valueOf(count));
            zoneDetails.setZoneType("RED");
        }else if (count>0 &&count<5)
        {
            zoneDetails.setNumCases(String.valueOf(count));
            zoneDetails.setZoneType("ORANGE");
        }else{
            zoneDetails.setNumCases(String.valueOf(count));
            zoneDetails.setZoneType("GREEN");
        }
        return zoneDetails;
    }
}
