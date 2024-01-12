package com.testemobile.githubjava.NetWork

import com.testemobile.githubjava.Model.GitHubRepo
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query


interface RequestRepoEndpoint {
    companion object{

        private const val REPO_ENDPOINT =
            "/search/repositories?q=language:Java&sort=stars&"

    }

    @GET(REPO_ENDPOINT)
    fun getItems(@Query("page=1") numberPage: String?) : Maybe<GitHubRepo>

}
