package com.udaan.test.repository;

import com.udaan.test.model.Registration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Configuration
public interface UserRepository extends CrudRepository<Registration, Integer> {


    List<Registration> findByPincode(String pincode);
}