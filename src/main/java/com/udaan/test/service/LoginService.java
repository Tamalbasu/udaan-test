package com.udaan.test.service;

import com.udaan.test.model.Login;

import java.util.Map;

public interface LoginService {

    public Map<String,String> addUser(Login login , Boolean admin);
}
