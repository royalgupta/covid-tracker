package com.example.covidtracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {
    static final String BaseUrl="https://disease.sh/v3/covid-19/";
  @GET("countries")
    Call<List<responsemodel>> countrydata();

}
