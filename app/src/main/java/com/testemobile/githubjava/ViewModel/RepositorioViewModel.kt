package com.testemobile.githubjava.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Retrofit.RepoGetService
import com.testemobile.githubjava.Retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioViewModel:ViewModel()  {

    private lateinit var items: ItemsModel
    private var listItems = MutableLiveData<ItemsModel>()
    val mitems: LiveData<ItemsModel> = listItems

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
                response.body()!!.items.getItems()
            }

        })
    }

  private fun List<ItemsModel>.getItems()= map{

      ItemsModel(

          nomeRepositorio = it.nomeRepositorio,
          descricaoRepositorio = it.descricaoRepositorio,
          nomeAutor = it.nomeAutor,
          fotoAutor = it.fotoAutor,
          numeroStars= it.numeroStars,
          numeroForks = it.numeroForks,
          owner = it.owner
      )
  }
}