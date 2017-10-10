package com.debut.ellipsis.freehit;


public class CountryHash {

    public String getCountrySN(String CountryName) {
        switch (CountryName) {
            case "INDIA":
                return "IND";
            case "AUSTRALIA":
                return "AUS";
            case "PAKISTAN":
                return "PAK";
            case "SRI LANKA":
                return "SL";
            case "SOUTH AFRICA":
                return "SA";
            case "BANGLADESH":
                return "BAN";
            case "NEW ZEALAND":
                return "NZ";
            case "ENGLAND":
                return "ENG";
            case "WEST INDIES":
                return "WI";
            case "ZIMBABWE":
                return "ZIM";
            case "CANADA":
                return "CAN";
            case "IRELAND":
                return "IRE";
            case "KENYA":
                return "KEN";
            case "NETHERLANDS":
                return "NED";
            case "SCOTLAND":
                return "SCO";
            case "HONG KONG":
                return "HK";
            case "UNITED ARAB EMIRATES":
                return "UAE";
            case "USA":
                return "USA";
            case "AFGHANISTAN":
                return "AFG";
            case "PAPUA NEW GUINEA":
                return "PNG";
            case "NEPAL":
                return "NEP";
            case "OMAN":
                return "OMN";
            case "WORLD XI":
                return "WXI";

        }
        return null;
    }

    public int getCountryFlag(String CountryName)
    {
        switch (CountryName) {
            case "INDIA":
                return R.drawable.flag_ind;
            case "AUSTRALIA":
                return R.drawable.flag_aus;
            case "PAKISTAN":
                return R.drawable.flag_pak;
            case "SRI LANKA":
                return R.drawable.flag_sl;
            case "SOUTH AFRICA":
                return R.drawable.flag_sa;
            case "BANGLADESH":
                return R.drawable.flag_ban;
            case "NEW ZEALAND":
                return R.drawable.flag_nz;
            case "ENGLAND":
                return R.drawable.flag_eng;
            case "WEST INDIES":
                return R.drawable.flag_wi;
            case "ZIMBABWE":
                return R.drawable.flag_wi;
            case "CANADA":
                return R.drawable.matches;
            case "IRELAND":
                return R.drawable.flag_ire;
            case "KENYA":
                return R.drawable.matches;
            case "NETHERLANDS":
                return R.drawable.matches;
            case "SCOTLAND":
                return R.drawable.matches;
            case "HONG KONG":
                return R.drawable.matches;
            case "UNITED ARAB EMIRATES":
                return R.drawable.matches;
            case "USA":
                return R.drawable.matches;
            case "AFGHANISTAN":
                return R.drawable.flag_afg;
            case "PAPUA NEW GUINEA":
                return R.drawable.matches;
            case "NEPAL":
                return R.drawable.matches;
            case "OMAN":
                return R.drawable.matches;
            case "WORLD XI":
                return R.drawable.matches;

        }
        return R.drawable.matches;

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

