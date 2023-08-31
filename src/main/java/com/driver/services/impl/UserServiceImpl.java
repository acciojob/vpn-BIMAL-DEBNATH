package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        User user=new User(username,password,false);

        Country country=new Country();
        country.enrich(countryName);

        country.setUser(user);
        user.setOriginalCountry(country);

        user=userRepository.save(user); //saving for get user id because while save user id create
        user.setOriginalIp(user.getOriginalCountry().getCode()+"."+user.getId());

        countryRepository3.save(country);
        return user;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {

        Optional<ServiceProvider>optionalServiceProvider=serviceProviderRepository3.findById(serviceProviderId);
        Optional<User>optionalUser=userRepository.findById(userId);

        ServiceProvider serviceProvider=optionalServiceProvider.get();
        User user=optionalUser.get();

        serviceProvider.getUsers().add(user);
        user.getServiceProviderList().add(serviceProvider);
        serviceProviderRepository3.save(serviceProvider);
        return user;

    }
}
