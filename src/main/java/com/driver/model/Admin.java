package com.driver.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    private String name;

    private  String password;


    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<ServiceProvider>serviceProviderList=new ArrayList<>();

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

}
