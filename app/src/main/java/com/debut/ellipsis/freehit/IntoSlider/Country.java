package com.debut.ellipsis.freehit.IntoSlider;


import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Country {

    public static final Country[] COUNTRIES = {
            new Country("AFG", "Afghanistan", "https://s.ndtvimg.com/images/entities/120/afghanistan-2156.png"),
            new Country("AUS", "Australia", "https://s.ndtvimg.com/images/entities/120/australia-7.png"),
            new Country("BAN", "Bangladesh", "https://s.ndtvimg.com/images/entities/120/bangladesh-2114.png"),
            new Country("ENG", "England", "https://s.ndtvimg.com/images/entities/120/england-5.png"),
            new Country("IRE", "Ireland", "https://s.ndtvimg.com/images/entities/120/ireland-2123.png"),
            new Country("IND", "India", "https://s.ndtvimg.com/images/entities/120/india-6.png"),
            new Country("NZ", "New Zealand", "https://s.ndtvimg.com/images/entities/120/new-zealand-2115.png"),
            new Country("PAK", "Pakistan", "https://s.ndtvimg.com/images/entities/120/pakistan-2116.png"),
            new Country("SA", "South Africa", "https://s.ndtvimg.com/images/entities/120/south-africa-2117.png"),
            new Country("SL", "Sri Lanka", "https://s.ndtvimg.com/images/entities/120/sri-lanka-2118.png"),
            new Country("WI", "West Indies", "https://s.ndtvimg.com/images/entities/120/west-indies-2119.png"),
            new Country("ZW", "Zimbabwe", "https://s.ndtvimg.com/images/entities/120/zimbabwe-2120.png"),
    };

    private String code;
    private String name;
    private String dialCode;
    private String flag ;

    public Country(String code, String name, String flag) {
        this.code = code;
        this.name = name;
        this.flag = flag;
    }

    public Country() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        if (TextUtils.isEmpty(name)) {
            name = new Locale("", code).getDisplayName();
        }
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }



    /*
     *      GENERIC STATIC FUNCTIONS
     */

    private static List<Country> allCountriesList;

    public static List<Country> getAllCountries() {
        if (allCountriesList == null) {
            allCountriesList = Arrays.asList(COUNTRIES);
        }
        return allCountriesList;
    }

}