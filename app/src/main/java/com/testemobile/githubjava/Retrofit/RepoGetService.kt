package com.testemobile.githubjava.Retrofit

import com.testemobile.githubjava.Model.GitHubRepo
import retrofit2.Call
import retrofit2.http.GET


interface RepoGetService {
    companion object{

        private const val SEARCH_REPOSITORY =
            "/search/repositories?q=language:Java&sort=stars&page=1/"

    }

    @GET(SEARCH_REPOSITORY)
    fun getItems(): Call<GitHubRepo>

}
