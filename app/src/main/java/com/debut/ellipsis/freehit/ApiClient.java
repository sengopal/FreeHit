package com.debut.ellipsis.freehit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://freehitapi-env.6s5kii7dbh.ap-south-1.elasticbeanstalk.com/";
    public static final String TEST_URL = "http://freehit-test-dev.ap-south-1.elasticbeanstalk.com/";
    public static final String HEROKU_TEST = "http://freehit-api.herokuapp.com/";
    private static Retrofit retrofit = null;

    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180,TimeUnit.SECONDS)
            .build();


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HEROKU_TEST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}