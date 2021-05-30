package com.example.cricketteamsdetailsapp.respository.webservice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchDetailsApi
{
    @GET("sifeeds/cricket/live/json/nzin01312019187360.json")
    Call<ResponseBody> getMatchDetails();
}
