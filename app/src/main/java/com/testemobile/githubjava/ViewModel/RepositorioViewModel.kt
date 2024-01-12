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
    private var locallistitens = MutableLiveData<List<ItemsModelRepo>>()
    private var listpull = MutableLiveData<List<PullRequestModel>>()

    val items: LiveData<List<ItemsModel>>  get()=listitens
    val pullitems: LiveData<List<PullRequestModel>>  get()=listpull

    fun LocalregistryItems(items: List<ItemsModel>){

        for(item in items){
            val itemsRepo = ItemsModelRepo().apply {

                    this.nomeRepositorio      = item.nomeRepositorio.toString()
                    this.descricaoRepositorio = item.descricaoRepositorio.toString()
                    this.nomeAutor            = item.nomeAutor.toString()
                    this.fotoAutor            = item.fotoAutor.toString()
                    this.numeroForks          = item.numeroForks.toString()
                    this.numeroStars          = item.numeroStars.toString()
            }
            repository.save(itemsRepo)
        }
    }
   fun listAllLocalItems(){ locallistitens.value= repository.listAll() }
  }
