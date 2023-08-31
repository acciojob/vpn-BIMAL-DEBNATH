package com.driver.model;// Note: Do not write @Enumerated annotation above CountryName in this model.

import javax.persistence.*;

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private CountryName countryName;
    private String code;

    @OneToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;



    public Country() {
    }

    public Country(CountryName countyName, String code) {
        this.countryName = countyName;
        this.code = code;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    public void enrich (String countryName) throws Exception {
        String updatedName = countryName.toUpperCase();
        if (updatedName.equals("IND")) {
            this.setCountryName(CountryName.IND);
            this.setCode(CountryName.IND.toCode());
        } else if (updatedName.equals("USA")) {
            this.setCountryName(CountryName.USA);
            this.setCode(CountryName.USA.toCode());
        } else if (updatedName.equals("AUS")) {
            this.setCountryName(CountryName.AUS);
            this.setCode(CountryName.AUS.toCode());
        } else if (updatedName.equals("CHI")) {
            this.setCountryName(CountryName.CHI);
            this.setCode(CountryName.CHI.toCode());
        } else if (updatedName.equals("JPN")) {
            this.setCountryName(CountryName.JPN);
            this.setCode(CountryName.JPN.toCode());
        } else {
            throw new Exception("Country not found");
        }
    }
}