package com.debut.ellipsis.freehit;


public class CountryHash {

    public String getCountrySN(String CountryName) {
        switch (CountryName) {
            case "India":
                return "IND";
            case "Australia":
                return "AUS";
            case "Pakistan":
                return "PAK";
            case "Sri Lanka":
                return "SL";
            case "South Africa":
                return "SA";
            case "Bangladesh":
                return "BAN";
            case "New Zealand":
                return "NZ";
            case "England":
                return "ENG";
            case "West Indies":
                return "WI";
            case "Zimbabwe":
                return "ZIM";
            case "Canada":
                return "CAN";
            case "Ireland":
                return "IRE";
            case "Kenya":
                return "KEN";
            case "Netherlands":
                return "NED";
            case "Scotland":
                return "SCO";
            case "Hong Kong":
                return "HK";
            case "United Arab Emirates":
                return "UAE";
            case "USA":
                return "USA";
            case "Afghanistan":
                return "AFG";
            case "Papua New Guinea":
                return "PNG";
            case "Nepal":
                return "NEP";
            case "Oman":
                return "OMN";
            case "World XI":
                return "WXI";

        }
        return null;
    }

    public String getCountryName(String CountrySN) {
        switch (CountrySN) {
            case "IND":
                return "India";
            case "AUS":
                return "Australia";
            case "PAK":
                return "Pakistan";
            case "SL":
                return "Sri Lanka";
            case "SA":
                return "South Africa";
            case "BAN":
                return "Bangladesh";
            case "NA":
                return "New Zealand";
            case "ENG":
                return "England";
            case "WI":
                return "West Indies";
            case "ZIM":
                return "Zimbabwe";
            case "CAN":
                return "Canada";
            case "IRE":
                return "Ireland";
            case "KEN":
                return "Kenya";
            case "NED":
                return "Netherlands";
            case "SCO":
                return "Scotland";
            case "HK":
                return "Hong Kong";
            case "UAE":
                return "United Arab Emirates";
            case "USA":
                return "USA";
            case "AFG":
                return "Afghanistan";
            case "PNG":
                return "Papua New Guinea";
            case "NEP":
                return "Nepal";
            case "OMN":
                return "Oman";
            case "WXI":
                return "World XI";

        }
        return null;
    }

}

