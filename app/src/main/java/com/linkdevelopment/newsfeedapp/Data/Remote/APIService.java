package com.linkdevelopment.newsfeedapp.Data.Remote;

/**
 * Created by Marwan on 12/20/2017.
 */

import com.linkdevelopment.newsfeedapp.Data.Model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Call<APIResponse> GetArticles(@Url String url);

}