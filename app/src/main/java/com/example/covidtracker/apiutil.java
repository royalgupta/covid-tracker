package com.example.covidtracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiutil {

    private static Retrofit retrofit=null;

    public static api getApiInterface(){

     if(retrofit==null)
     {
         retrofit= new Retrofit.Builder()
                 .baseUrl(api.BaseUrl)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
     }

        return retrofit.create(api.class);
    }
}
