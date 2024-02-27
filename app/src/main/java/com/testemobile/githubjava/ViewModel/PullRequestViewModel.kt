package com.testemobile.githubjava.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Model.User
import com.testemobile.githubjava.NetWork.PullRequestEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel(application : Application) : AndroidViewModel(application) {


    private val _pullrequest = MutableLiveData<List<PullRequestModel>>()
    val pullrequest : LiveData<List<PullRequestModel>> get() = _pullrequest
    private lateinit var listpullrequest : MutableList<PullRequestModel>

    fun chargeListOfPullRequest(autor: String, repo: String){

        viewModelScope.launch(Dispatchers.IO){

            try{

                val remote = RetrofitService.createService(PullRequestEndpoint::class.java)
                val response= remote.getPullRequest(autor,repo)

                listpullrequest = mutableListOf()


                if(response.isSuccessful){

                    val objeto = response.body()?.asJsonArray
                    var i=0

                    objeto?.asJsonArray?.forEach {
                        val listausuariospullrequest = objeto.asJsonArray?.get(i)
                        val usuariopullrequest = listausuariospullrequest?.asJsonObject

                        listpullrequest.add(PullRequestModel(
                            tituloPullRequests = formataString(usuariopullrequest?.asJsonObject?.get("title").toString()),
                            dataPullRequests = formataDataString(usuariopullrequest?.asJsonObject?.get("created_at").toString()),
                            body = formataString(usuariopullrequest?.asJsonObject?.get("body").toString()) ,
                            user = User(login = formataString(usuariopullrequest?.getAsJsonObject("user")?.get("login").toString())),
                        ))
                        i++
                    }

                    _pullrequest.postValue(listpullrequest)
                }

            }catch(e:Exception){
                e.message?.let { Log.d(R.string.pull_request_error.toString(),it)
            }

        }
    }

}



    private fun formataString(text: String): String {
        var textModified = text.substring(1, text.length - 1)
        return textModified
    }

    private fun formataDataString(dataText: String): String {
        var textModified = dataText.substring(1, dataText.length - 1)
        textModified = textModified.substring(0,10)
        return textModified
    }

}
