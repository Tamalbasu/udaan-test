package com.udaan.test.service.impl;

import com.udaan.test.model.Login;
import com.udaan.test.model.Registration;
import com.udaan.test.repository.UserRepository;
import com.udaan.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;
    @Override
    public Map<String, String> addUser(Login login ,Boolean admin) {
        Registration registration = new Registration();
        registration.setName(login.getName());
        registration.setPhone(login.getPhone());
        registration.setPincode(login.getPinCode());
        if(admin){
            registration.setAdmin("1");
        }else{
            registration.setAdmin("0");
        }
        Registration registration1 = userRepository.save(registration);
        Map<String,String> data = new HashMap<>();
        if(admin){
            data.put("adminuserID", String.valueOf(registration1.getId()));
        }else{
            data.put("userID", String.valueOf(registration1.getId()));
        }
        return data;
    }
}
