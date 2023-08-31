package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String userName;
  private String password;
  private boolean connected;
  private String maskedIp;
  private String originalIp;


   @ManyToMany
   @JoinColumn
   private List<ServiceProvider>serviceProviderList=new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Country originalCountry; //this file will remain unaffected even connection made;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Connection>connectionList=new ArrayList<>();

    public User() {
    }

    public User(String name, String password, boolean connected) {
        this.userName = name;
        this.password = password;
        this.connected = connected;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getMaskedIp() {
        return maskedIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }

    public String getOriginalIp() {
        return originalIp;
    }

    public void setOriginalIp(String originalIp) {
        this.originalIp = originalIp;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public Country getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(Country originalCountry) {
        this.originalCountry = originalCountry;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

}
