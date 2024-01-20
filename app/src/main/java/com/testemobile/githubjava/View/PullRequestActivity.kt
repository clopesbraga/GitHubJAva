package com.testemobile.githubjava.View

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.PullRequestAdapter
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Model.User
import com.testemobile.githubjava.NetWork.PullRequestEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.ActivityPullRequestBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullRequestActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPullRequestBinding
    private lateinit var criador : String
    private lateinit var repositorio : String
    private lateinit var viewmodel : RepositorioViewModel
    private lateinit var toolbar : Toolbar
    private lateinit var adapter: PullRequestAdapter
    private lateinit var listpullrequest : MutableList<PullRequestModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intialSetup()

        criador = intent.getStringExtra("criador").toString()
        repositorio= intent.getStringExtra("repositorio").toString()

        listpullrequest = mutableListOf()

    }

    override fun onResume() {
        super.onResume()

        chargeListOfPullRequest(criador,repositorio)
    }

    fun chargeListOfPullRequest(autor: String, repo: String) {

        val remote = RetrofitService.createService(PullRequestEndpoint::class.java)
        val response= remote.getPullRequest(autor,repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val objeto = it.asJsonArray
                var i=0
                try{
                    objeto?.asJsonArray?.forEach {
                        val listausuariospullrequest = objeto?.asJsonArray?.get(i)
                        val usuariopullrequest = listausuariospullrequest?.asJsonObject

                        listpullrequest.add(PullRequestModel(
                            tituloPullRequests = formataString(usuariopullrequest?.asJsonObject?.get("title").toString()),
                            dataPullRequests = formataDataString(usuariopullrequest?.asJsonObject?.get("created_at").toString()),
                            body = formataString(usuariopullrequest?.asJsonObject?.get("body").toString()) ,
                            user = User(login = formataString(usuariopullrequest?.getAsJsonObject("user")?.get("login").toString())),
                        ))
                        i++
                    }

                }catch(e:Exception){
                    e.message?.let { Log.d("PULLREQUEST_ERROR",it) }
                    Toast.makeText(
                        this, R.string.list_pullrequesters_error, Toast.LENGTH_LONG
                    ).show()
                }
                adapter = PullRequestAdapter(listpullrequest)
                _binding.ltvPullRequest.adapter= adapter

            },{ it ->
                it.message?.let { Log.d("PULLREQUEST_ERROR",it) }
                Toast.makeText(
                    this, R.string.list_pullrequesters_error, Toast.LENGTH_LONG
                ).show()
            })
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

    private fun intialSetup(){

        viewmodel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
        _binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setUpBar()

        _binding.ltvPullRequest.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpBar(){
        toolbar = _binding.toolbar

        val titleTextColor = _binding.toolbar.resources.getColor(R.color.white)
        val leftArrow = _binding.toolbar.resources.getDrawable(com.google.android.material.R.drawable.material_ic_keyboard_arrow_left_black_24dp)
        leftArrow.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(leftArrow)

        toolbar.setTitleTextColor(titleTextColor)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}