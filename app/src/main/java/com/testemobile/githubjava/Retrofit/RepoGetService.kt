package com.testemobile.githubjava.Retrofit

import com.testemobile.githubjava.Model.RepositorioModel
import retrofit2.Call
import retrofit2.http.GET


interface RepoGetService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1/")
    fun list(): Call<List<RepositorioModel>>

}
