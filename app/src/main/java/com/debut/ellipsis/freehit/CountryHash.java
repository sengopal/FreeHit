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
                return "OMA";
            case "WORLD XI":
                return "WXI";
            case "ROYAL CHALLENGERS BANGALORE":
                return "RCB";
            case "KOLKATA KNIGHT RIDERS":
                return "KKR";
            case "KINGS XI PUNJAB":
                return "KXIP";
            case "DELHI DAREDEVILS":
                return "DD";
            case "MUMBAI INDIANS":
                return "MI";
            case "SUNRISERS HYDERABAD":
                return "SRH";
            case "RISING PUNE SUPERGIANT":
                return "RPS";
            case "GUJARAT LIONS":
                return "GL";

        }
        return null;
    }


    public String getCountryFlag(String CountryName)
    {
        switch (CountryName) {
            case "INDIA":
                return "https://s.ndtvimg.com/images/entities/120/india-6.png";
            case "AUSTRALIA":
                return "https://s.ndtvimg.com/images/entities/120/australia-7.png";
            case "PAKISTAN":
                return "https://s.ndtvimg.com/images/entities/120/pakistan-2116.png";
            case "SRI LANKA":
                return "https://s.ndtvimg.com/images/entities/120/sri-lanka-2118.png";
            case "SOUTH AFRICA":
                return "https://s.ndtvimg.com/images/entities/120/south-africa-2117.png";
            case "BANGLADESH":
                return "https://s.ndtvimg.com/images/entities/120/bangladesh-2114.png";
            case "NEW ZEALAND":
                return "https://s.ndtvimg.com/images/entities/120/new-zealand-2115.png";
            case "ENGLAND":
                return "https://s.ndtvimg.com/images/entities/120/england-5.png";
            case "WEST INDIES":
                return "https://s.ndtvimg.com/images/entities/120/west-indies-2119.png";
            case "ZIMBABWE":
                return "https://s.ndtvimg.com/images/entities/120/zimbabwe-2120.png";
            case "CANADA":
                return "https://s.ndtvimg.com/images/entities/120/canada-2122.png";
            case "IRELAND":
                return "https://s.ndtvimg.com/images/entities/120/ireland-2123.png";
            case "KENYA":
                return "https://s.ndtvimg.com/images/entities/120/kenya-2124.png";
            case "NETHERLANDS":
                return "https://s.ndtvimg.com/images/entities/120/netherlands-2125.png";
            case "SCOTLAND":
                return "https://s.ndtvimg.com/images/entities/120/scotland-2126.png";
            case "HONG KONG":
                return "https://s.ndtvimg.com/images/entities/120/hong-kong-2129.png";
            case "UNITED ARAB EMIRATES":
                return "https://s.ndtvimg.com/images/entities/120/united-arab-emirates-2131.png";
            case "USA":
                return "https://s.ndtvimg.com/images/entities/120/usa-2132.png";
            case "AFGHANISTAN":
                return "https://s.ndtvimg.com/images/entities/120/afghanistan-2156.png";
            case "PAPUA NEW GUINEA":
                return "https://s.ndtvimg.com/images/entities/120/papua-new-guinea-105091.png";
            case "NEPAL":
                return "https://s.ndtvimg.com/images/entities/120/nepal-105092.png";
            case "OMAN":
                return "https://s.ndtvimg.com/images/entities/120/oman-107053.png";
            case "WORLD XI":
                return "https://s.ndtvimg.com/images/entities/300/world-xi_636408269319224373.png";
            case "ROYAL CHALLENGERS BANGALORE":
                return "https://s.ndtvimg.com/images/entities/120/royal-challengers-bangalore-2138.png";
            case "KOLKATA KNIGHT RIDERS":
                return "https://s.ndtvimg.com/images/entities/300/kolkata-knight-riders-2139.png";
            case "KINGS XI PUNJAB":
                return "https://s.ndtvimg.com/images/entities/300/kings-xi-punjab-2140.png";
            case "DELHI DAREDEVILS":
                return "https://s.ndtvimg.com/images/entities/120/delhi-daredevils-2142.png";
            case "MUMBAI INDIANS":
                return "https://s.ndtvimg.com/images/entities/300/mumbai-indians-2144.png";
            case "SUNRISERS HYDERABAD":
                return "https://s.ndtvimg.com/images/entities/300/sunrisers-hyderabad-2165.png";
            case "RISING PUNE SUPERGIANT":
                return "https://s.ndtvimg.com/images/entities/300/rising-pune-supergiants-107044.png";
            case "GUJARAT LIONS":
                return "https://s.ndtvimg.com/images/entities/300/gujarat-lions-107045.png";
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
            case "NZ":
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
            case "OMA":
                return "Oman";
            case "WXI":
                return "World XI";
            case "RCB":
                return "ROYAL CHALLENGERS BANGALORE";
            case "KKR":
                return "KOLKATA KNIGHT RIDERS";
            case "KXIP":
                return "KINGS XI PUNJAB";
            case "DD":
                return "DELHI DAREDEVILS";
            case "MI":
                return "MUMBAI INDIANS";
            case "SRH":
                return "SUNRISERS HYDERABAD";
            case "RPS":
                return "RISING PUNE SUPERGIANT";
            case "GL":
                return "GUJARAT LIONS";
        }
        return null;
    }


}

