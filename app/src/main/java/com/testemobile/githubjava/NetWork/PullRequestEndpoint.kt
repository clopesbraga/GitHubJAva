package com.testemobile.githubjava.NetWork

import com.google.gson.JsonArray
import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestEndpoint {
    @GET("/repos/{criador}/{repositorio}/pulls")
  suspend  fun getPullRequest(
        @Path(value = "criador")criador :String,
        @Path(value = "repositorio") repositorio:String
    ): Response<JsonArray>

}
