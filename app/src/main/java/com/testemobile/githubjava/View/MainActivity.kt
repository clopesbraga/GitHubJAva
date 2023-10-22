package com.testemobile.githubjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Retrofit.RepoGetService
import com.testemobile.githubjava.Retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remote= RetrofitService.createService(RepoGetService::class.java)
        val call: Call<GitHubRepo> = remote.getItems()

        val response = call.enqueue(object : Callback<GitHubRepo>{

            override fun onFailure(call:Call<GitHubRepo>, t:Throwable){
                val s = t.message
            }

            override fun onResponse(
                call: Call<GitHubRepo>,
                response: Response<GitHubRepo>
            ) {
                val s = response.body()
            }

        })


    }
}