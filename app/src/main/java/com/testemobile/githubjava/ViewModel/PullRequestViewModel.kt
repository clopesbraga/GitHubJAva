package com.testemobile.githubjava.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.PullRequestLocalModel
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Model.RepositorioLocalModel
import com.testemobile.githubjava.Repository.PullRequestRepository

class PullRequestViewModel(application : Application): AndroidViewModel(application) {

    private val repository = PullRequestRepository(application.applicationContext)
    private var listpullrequests = MutableLiveData<List<PullRequestLocalModel>>()

    val pullrequests: LiveData<List<PullRequestLocalModel>> get()=listpullrequests


    fun verifyExistInLocalData(items: List<PullRequestModel>){

        for(item in items){
            val PullRequestLocalModel = PullRequestLocalModel().apply {


                this.titulo               = item.tituloPullRequests
                this.dataPullRequests     = item.dataPullRequests
                this.body                 = item.body
                this.user                 = item.user.toString()

            }
            if(repository.listAll().isEmpty()){

                repository.save(PullRequestLocalModel)

            }else{

                repository.update(PullRequestLocalModel)
            }
        }

    }

}