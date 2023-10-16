package com.testemobile.githubjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testemobile.githubjava.Model.RepositorioModel
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
        val call: Call<List<RepositorioModel>> = remote.list()

        val response = call.enqueue(object : Callback<List<RepositorioModel>>{

            override fun onFailure(call:Call<List<RepositorioModel>>,t:Throwable){
                val s = t.message
            }

            override fun onResponse(
                call: Call<List<RepositorioModel>>,
                response: Response<List<RepositorioModel>>
            ) {
                val s = response.body()
            }

        })


    }
}