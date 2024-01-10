package com.testemobile.githubjava.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.PullRequestAdapter
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Model.User
import com.testemobile.githubjava.NetWork.PullRequestEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.ActivityPullRequestBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullRequestActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPullRequestBinding
    private lateinit var criador : String
    private lateinit var repositorio : String
    private lateinit var viewmodel : RepositorioViewModel
    private lateinit var adapter: PullRequestAdapter
    private lateinit var listpullrequest : MutableList<PullRequestModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(RepositorioViewModel::class.java)

        _binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.ltvPullRequest.layoutManager = LinearLayoutManager(this)

        criador = intent.getStringExtra("criador").toString()
        repositorio= intent.getStringExtra("repositorio").toString()

        listpullrequest = mutableListOf()

    }

    override fun onResume() {
        super.onResume()

        chargeListOfPullRequest(criador,repositorio)
        observe()
    }

    fun chargeListOfPullRequest(autor: String, repo: String) {

        val remote = RetrofitService.createService(PullRequestEndpoint::class.java)
        val call= remote.getPullRequest(autor,repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val objeto = it.asJsonArray
                var i=0
                try{
                    objeto?.asJsonArray?.forEach {
                        val getUsers = objeto?.asJsonArray?.get(i)
                        val getUser = getUsers?.asJsonObject

                        listpullrequest.add(PullRequestModel(
                            tituloPullRequests = getUser?.asJsonObject?.get("title").toString(),
                            dataPullRequests = getUser?.asJsonObject?.get("created_at").toString(),
                            body = getUser?.asJsonObject?.get("body").toString() ,
                            user = User(login = getUser?.getAsJsonObject("user")?.get("login").toString()),
                        ))
                        i++
                    }

                }catch(e:Exception){
                    e.message
                }
                adapter = PullRequestAdapter(listpullrequest)
                _binding.ltvPullRequest.adapter= adapter

            },{
                val s = it.message
            })
    }

    fun formataString(text: String): String {
        var textModified = text.substring(1, text.length - 1)
        return textModified
    }

    private fun observe(){

        viewmodel.pullitems.observe(this){

            adapter.atualizaListaRepositorio(it)
        }

    }
}