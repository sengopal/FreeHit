package com.debut.ellipsis.freehit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://freehitapi-env.6s5kii7dbh.ap-south-1.elasticbeanstalk.com/";
    public static final String TEST_URL = "http://freehittest-env.3rp2pzz3e5.ap-south-1.elasticbeanstalk.com/";
    private static Retrofit retrofit = null;

    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .build();


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}