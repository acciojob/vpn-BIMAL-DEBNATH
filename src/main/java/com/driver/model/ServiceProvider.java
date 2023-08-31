package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceProviderId;

    private String name;

    @ManyToOne
    @JoinColumn
    private Admin admin;

    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    List<Country> countryList=new ArrayList<>();

    @ManyToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL) //check why
    List<User> userList=new ArrayList<>();

    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    List<Connection> connectionList=new ArrayList<>();  //check


    public ServiceProvider() {
    }

    public ServiceProvider(String name) {
        this.name = name;
    }


    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

}
