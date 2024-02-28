package com.testemobile.githubjava.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.RepositorioLocalModel
import com.testemobile.githubjava.NetWork.RequestRepoEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import com.testemobile.githubjava.Repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositorioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GithubRepository(application.applicationContext)
    private var listitens = MutableLiveData<List<ItemsModel>>()
    private val _gitHubRepo = MutableLiveData<GitHubRepo>()
    val gitHubRepo: LiveData<GitHubRepo> get() = _gitHubRepo

    val items: LiveData<List<ItemsModel>> get() = listitens

    fun populateListInUI(pagina: String) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val remote = RetrofitService.createService(RequestRepoEndpoint::class.java)
                val response = remote.getItems(pagina)

                if (response.isSuccessful) {

                    _gitHubRepo.postValue(response.body())
                    sendToLocalData(response.body()!!.items)
                }
            } catch (it: Exception) {
                it.message?.let { Log.d(R.string.repository_error.toString(), it) }
            }

        }

    }

    fun sendToLocalData(items: List<ItemsModel>) {

        for (item in items) {
            val repositoriLocalModel = RepositorioLocalModel().apply {

                this.id = item.id!!
                this.nomeRepositorio = item.nomeRepositorio.toString()
                this.descricaoRepositorio = item.descricaoRepositorio.toString()
                this.nomeAutor = item.nomeAutor.toString()
                this.fotoAutor = item.fotoAutor.toString()
                this.numeroForks = item.numeroForks.toString()
                this.numeroStars = item.numeroStars.toString()
            }

            populateOrUpdateLocalData(repositoriLocalModel, item)

        }
    }

    private fun populateOrUpdateLocalData(
        repositoriLocalModel: RepositorioLocalModel,
        item: ItemsModel
    ) {
        if (repository.getId(repositoriLocalModel.id) == item.id) {
            repository.update(repositoriLocalModel)
        } else {
            repository.save(repositoriLocalModel)
        }
    }
}


