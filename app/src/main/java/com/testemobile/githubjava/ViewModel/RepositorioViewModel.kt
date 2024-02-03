package com.testemobile.githubjava.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.RepositorioLocalModel
import com.testemobile.githubjava.Repository.GithubRepository

class RepositorioViewModel(application : Application):AndroidViewModel(application) {

    private val repository = GithubRepository(application.applicationContext)
    private var listitens = MutableLiveData<List<ItemsModel>>()

    val items: LiveData<List<ItemsModel>> get() = listitens

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


