package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user=userRepository2.findById(userId).get();
        if(user.isConnected()){
            throw new Exception("Already connected");
        }
        if(String.valueOf(user.getOriginalCountry().getCountryName()).equals(countryName)){
            return user;
        }

        Country country=countryRepository.findByName(countryName).get(); //do with new ei

        List<ServiceProvider> providerList=user.getServiceProviderList(); //OneToMany


        Integer smallestId=null;

        for(ServiceProvider serviceProvider:providerList){
            List<Country>countryList=serviceProvider.getCountryList();
            for(Country country1:countryList){
                if(country1.getCountryName().equals(country.getCountryName())){ //can use country code
                    if(smallestId==null || smallestId>serviceProvider.getId()){
                        smallestId=serviceProvider.getId();
                    }
                }
            }

        }
        if(smallestId==null){
            throw new Exception("Unable to connect");
        }

        user.setMaskedIp(country.getCode()+"."+smallestId+"."+userId);
        user.setConnected(true);

        ServiceProvider serviceProvider=serviceProviderRepository2.findById(smallestId).get();
        Connection connection=new Connection(); //save first
        connection.setServiceProvider(serviceProvider);
        connection.setUser(user);

        user.getConnected().add(connection);
        serviceProvider.getConnectionList().add(connection);

        //we have to save to parent

        userRepository2.save(user);
        serviceProviderRepository2.save(serviceProvider);

        return user;

    }
    @Override
    public User disconnect(int userId) throws Exception {

        User user=userRepository2.findById(userId).get();

        if(!user.isConnected()){
            throw new Exception("Already disconnected");
        }


        user.setMaskedIp(null);
        user.setConnected(false);

           userRepository2.save(user);
          return user;
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {

        User sender=userRepository2.findById(senderId).get();
        User receiver=userRepository2.findById(receiverId).get();

        Country receiverCurrCountry=null;

        if(!receiver.isConnected()){
            receiverCurrCountry=receiver.getOriginalCountry();
        }else {
            String countryCode[] = receiver.getMaskedIp().split(".");

            int code=Integer.parseInt(countryCode[0]);

            receiverCurrCountry=countryRepository.findCountryByCode(code);
        }


        if(sender.getOriginalCountry().equals(receiverCurrCountry)){
            return  sender;
        }

        User user=null;
        try {
           user = connect(senderId,String.valueOf(receiverCurrCountry.getCountryName()));
        }catch (Exception e){
            throw  new Exception("Cannot establish communication");
        }

        return  user;

    }
}
