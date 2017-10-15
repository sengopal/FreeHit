package com.debut.ellipsis.freehit.IntoSlider;


import java.util.Arrays;
import java.util.List;


public class Country {

    private static final Country[] COUNTRIES = {
            new Country("Afghanistan", "https://s.ndtvimg.com/images/entities/120/afghanistan-2156.png"),
            new Country("Australia", "https://s.ndtvimg.com/images/entities/120/australia-7.png"),
            new Country("Bangladesh", "https://s.ndtvimg.com/images/entities/120/bangladesh-2114.png"),
            new Country("Canada", "https://s.ndtvimg.com/images/entities/120/canada-2122.png"),
            new Country("England", "https://s.ndtvimg.com/images/entities/120/england-5.png"),
            new Country("Ireland", "https://s.ndtvimg.com/images/entities/120/ireland-2123.png"),
            new Country("India", "https://s.ndtvimg.com/images/entities/120/india-6.png"),
            new Country("Netherlands", "https://s.ndtvimg.com/images/entities/120/netherlands-2125.png"),
            new Country("New Zealand", "https://s.ndtvimg.com/images/entities/120/new-zealand-2115.png"),
            new Country("Pakistan", "https://s.ndtvimg.com/images/entities/120/pakistan-2116.png"),
            new Country("South Africa", "https://s.ndtvimg.com/images/entities/120/south-africa-2117.png"),
            new Country("Sri Lanka", "https://s.ndtvimg.com/images/entities/120/sri-lanka-2118.png"),
            new Country("United Arab Emirates", "https://s.ndtvimg.com/images/entities/120/united-arab-emirates-2131.png"),
            new Country("West Indies", "https://s.ndtvimg.com/images/entities/120/west-indies-2119.png"),
            new Country("Zimbabwe", "https://s.ndtvimg.com/images/entities/120/zimbabwe-2120.png"),
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