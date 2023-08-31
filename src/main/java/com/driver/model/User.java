package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private boolean isConnected;
  private String maskedIp;
  private String originalIp;


   @ManyToMany
   @JoinColumn
   private List<ServiceProvider>serviceProviderList=new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Country originalCountry; //this file will remain unaffected even connection made;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Connection> connected =new ArrayList<>();

    public User() {
    }

    public User(String name, String password, boolean connected) {
        this.username = name;
        this.password = password;
        this.isConnected = connected;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
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

    public List<Connection> getConnected() {
        return connected;
    }

    public void setConnected(List<Connection> connected) {
        this.connected = connected;
    }

}
