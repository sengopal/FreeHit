package com.debut.ellipsis.freehit.IntoSlider;


import java.util.Arrays;
import java.util.List;


public class Country {

    private static final Country[] COUNTRIES = {
            new Country("Afghanistan", WelcomeActivity.countryHash.getCountryFlag("AFGHANISTAN")),
            new Country("Australia", WelcomeActivity.countryHash.getCountryFlag("AUSTRALIA")),
            new Country("Bangladesh", WelcomeActivity.countryHash.getCountryFlag("BANGLADESH")),
            new Country("Canada", WelcomeActivity.countryHash.getCountryFlag("CANADA")),
            new Country("England", WelcomeActivity.countryHash.getCountryFlag("ENGLAND")),
            new Country("Ireland", WelcomeActivity.countryHash.getCountryFlag("IRELAND")),
            new Country("India", WelcomeActivity.countryHash.getCountryFlag("INDIA")),
            new Country("Netherlands", WelcomeActivity.countryHash.getCountryFlag("NETHERLANDS")),
            new Country("New Zealand", WelcomeActivity.countryHash.getCountryFlag("NEW ZEALAND")),
            new Country("Pakistan", WelcomeActivity.countryHash.getCountryFlag("PAKISTAN")),
            new Country("South Africa", WelcomeActivity.countryHash.getCountryFlag("SOUTH AFRICA")),
            new Country("Sri Lanka", WelcomeActivity.countryHash.getCountryFlag("SRI LANKA")),
            new Country("United Arab Emirates", WelcomeActivity.countryHash.getCountryFlag("UNITED ARAB EMIRATES")),
            new Country("West Indies", WelcomeActivity.countryHash.getCountryFlag("WEST INDIES")),
            new Country("Zimbabwe", WelcomeActivity.countryHash.getCountryFlag("ZIMBABWE")),
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

    private static List<Country> allCountriesList;

    static List<Country> getAllCountries() {
        if (allCountriesList == null) {
            allCountriesList = Arrays.asList(COUNTRIES);
        }
        return allCountriesList;
    }

}