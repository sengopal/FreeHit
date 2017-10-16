package com.debut.ellipsis.freehit.IntoSlider;


import com.debut.ellipsis.freehit.CountryHash;

import java.util.Arrays;
import java.util.List;


public class Country {

    private static CountryHash countryHash = new CountryHash();

    private static final Country[] COUNTRIES = {
            new Country("Afghanistan", countryHash.getCountryFlag("AFGHANISTAN")),
            new Country("Australia", countryHash.getCountryFlag("AUSTRALIA")),
            new Country("Bangladesh", countryHash.getCountryFlag("BANGLADESH")),
            new Country("Canada", countryHash.getCountryFlag("CANADA")),
            new Country("England", countryHash.getCountryFlag("ENGLAND")),
            new Country("Ireland", countryHash.getCountryFlag("IRELAND")),
            new Country("India", countryHash.getCountryFlag("INDIA")),
            new Country("Netherlands", countryHash.getCountryFlag("NETHERLANDS")),
            new Country("New Zealand", countryHash.getCountryFlag("NEW ZEALAND")),
            new Country("Pakistan", countryHash.getCountryFlag("PAKISTAN")),
            new Country("South Africa", countryHash.getCountryFlag("SOUTH AFRICA")),
            new Country("Sri Lanka", countryHash.getCountryFlag("SRI LANKA")),
            new Country("United Arab Emirates", countryHash.getCountryFlag("UNITED ARAB EMIRATES")),
            new Country("West Indies", countryHash.getCountryFlag("WEST INDIES")),
            new Country("Zimbabwe", countryHash.getCountryFlag("ZIMBABWE")),
    };

    private String name;
    private String flag ;

    public Country( String name, String flag) {

        this.name = name;
        this.flag = flag;
    }

    public Country() {
    }


    public String getName() {
        return name;
    }

    String getFlag() {
        return flag;
    }



    /* GENERIC STATIC FUNCTIONS*/
    private static List<Country> allCountriesList;

    static List<Country> getAllCountries() {
        if (allCountriesList == null) {
            allCountriesList = Arrays.asList(COUNTRIES);
        }
        return allCountriesList;
    }

}