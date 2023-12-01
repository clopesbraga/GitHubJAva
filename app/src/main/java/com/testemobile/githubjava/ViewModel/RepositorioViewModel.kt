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
import com.testemobile.githubjava.Retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioViewModel(application : Application):AndroidViewModel(application)  {

    private val repository = GithubRepository(application.applicationContext)
    private var listitens = MutableLiveData<List<ItemsModelRepo>>()

    val items: LiveData<List<ItemsModelRepo>>  get()=listitens


    fun requestGitHubRepo(){
        val remote= RetrofitService.createService(RepoGetService::class.java)
        val call: Call<GitHubRepo> = remote.getItems()
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

        for(i in 0 ..29){
            val itemsRepo = ItemsModelRepo().apply {

                    this.nomeRepositorio      = items[i].nomeRepositorio.toString()
                    this.descricaoRepositorio = items[i].descricaoRepositorio.toString()
                    this.nomeAutor            = items[i].nomeAutor.toString()
                    this.fotoAutor            = items[i].fotoAutor.toString()
                    this.numeroForks          = items[i].numeroForks.toString()
                    this.numeroStars          = items[i].numeroStars.toString()

            }
            repository.save(itemsRepo)
        }
    }

    fun listAllItems(){

       listitens.value= repository.listAll()
    }

  }
