package com.testemobile.githubjava.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object{

        private lateinit var  retrofit: Retrofit
        private  var  baseUrl ="https://api.github.com"

        private fun getRetrofitInstance():Retrofit{

            val httpClient = OkHttpClient.Builder()

            if(!::retrofit.isInitialized){

                retrofit=Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
        fun<S> createService(servicesClass:Class<S>):S{
            return getRetrofitInstance().create(servicesClass)
        }

    }

}