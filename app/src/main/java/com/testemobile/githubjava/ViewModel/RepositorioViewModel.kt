package com.testemobile.githubjava.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.ItemsModelRepo
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Repository.GithubRepository

class RepositorioViewModel(application : Application):AndroidViewModel(application){

    private val repository = GithubRepository(application.applicationContext)
    private var listitens = MutableLiveData<List<ItemsModel>>()

    val items: LiveData<List<ItemsModel>>  get()=listitens

    fun verifyExistInLocalData(items: List<ItemsModel>){

        for(item in items){
            val itemsRepo = ItemsModelRepo().apply {

                    this.id                   = item.id
                    this.nomeRepositorio      = item.nomeRepositorio.toString()
                    this.descricaoRepositorio = item.descricaoRepositorio.toString()
                    this.nomeAutor            = item.nomeAutor.toString()
                    this.fotoAutor            = item.fotoAutor.toString()
                    this.numeroForks          = item.numeroForks.toString()
                    this.numeroStars          = item.numeroStars.toString()
            }
            if(repository.listAll().isEmpty()){

                repository.save(itemsRepo)

            }else{repository.update(itemsRepo)}
        }

    }

  }
