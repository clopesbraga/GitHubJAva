package com.testemobile.githubjava.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.ItemsModelRepo
import com.testemobile.githubjava.Repository.GithubRepository
import com.testemobile.githubjava.Retrofit.RepoGetService
import com.testemobile.githubjava.Retrofit.RequestRepoEndpoint
import com.testemobile.githubjava.Retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioViewModel(application : Application):AndroidViewModel(application){

    private val repository = GithubRepository(application.applicationContext)
    private var listitens = MutableLiveData<List<ItemsModelRepo>>()

    val items: LiveData<List<ItemsModelRepo>>  get()=listitens

    fun requestGitHubRepo(){
        val remote= RetrofitService.createService(RequestRepoEndpoint::class.java)
        val call: Call<GitHubRepo> = remote.getItems("2")
        val response = call.enqueue(object : Callback<GitHubRepo> {

            override fun onFailure(call: Call<GitHubRepo>, t:Throwable){
                val s = t.message
            }

            override fun onResponse(
                call: Call<GitHubRepo>,
                response: Response<GitHubRepo>
            ) {
//                response.body()!!.items.getItems()
                registryItems(response.body()!!.items.getItems())

            }
        })
    }

  private fun List<ItemsModel>.getItems()= map{

      ItemsModel(
          nomeRepositorio = it.nomeRepositorio,
          descricaoRepositorio = it.descricaoRepositorio,
          nomeAutor = it.owner?.login?:"",
          fotoAutor = it.owner?.avatarUrl?:"",
          numeroStars= it.numeroStars,
          numeroForks = it.numeroForks,
          owner = it.owner
      )
  }

    fun registryItems(items: List<ItemsModel>){

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
    fun listAllItems(){ listitens.value= repository.listAll() }
  }
