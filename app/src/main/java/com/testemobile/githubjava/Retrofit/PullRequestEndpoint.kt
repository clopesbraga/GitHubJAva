package com.testemobile.githubjava.Retrofit

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.testemobile.githubjava.Model.PullRequestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PullRequestEndpoint {
    @GET("/repos/{criador}/{repositorio}/pulls")
    fun getPullRequest(
        @Path(value = "criador")criador :String,
        @Path(value = "repositorio") repositorio:String
    ):Call<JsonArray>

}
