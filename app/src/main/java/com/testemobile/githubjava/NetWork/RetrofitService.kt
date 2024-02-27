package com.testemobile.githubjava.NetWork

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object{

        private lateinit var  retrofit: Retrofit
        private  var  baseUrl ="https://api.github.com"

        private fun getNetWorkInstance():Retrofit{

            val httpClient = OkHttpClient.Builder()

            if(!::retrofit.isInitialized){

                retrofit=Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }
        fun<S> createService(servicesClass:Class<S>):S{
            return getNetWorkInstance().create(servicesClass)
        }

    }

}