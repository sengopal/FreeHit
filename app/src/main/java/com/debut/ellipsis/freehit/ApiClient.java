package com.debut.ellipsis.freehit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://freehitapi-env.6s5kii7dbh.ap-south-1.elasticbeanstalk.com/";
    public static final String URL = "http://freehittest-env.3rp2pzz3e5.ap-south-1.elasticbeanstalk.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}