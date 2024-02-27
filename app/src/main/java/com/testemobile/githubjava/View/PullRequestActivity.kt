package com.testemobile.githubjava.View

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.View.Adapter.PullRequestAdapter
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.Model.User
import com.testemobile.githubjava.NetWork.PullRequestEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import com.testemobile.githubjava.ViewModel.PullRequestViewModel
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.ActivityPullRequestBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPullRequestBinding
    private lateinit var criador: String
    private lateinit var repositorio: String
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: PullRequestAdapter
    private val viewmodel: PullRequestViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialSetup()

        criador = intent.getStringExtra("criador").toString()
        repositorio = intent.getStringExtra("repositorio").toString()

    }

    override fun onResume() {
        super.onResume()
        viewmodel.chargeListOfPullRequest(criador, repositorio)
        observer()
    }

    private fun observer() {

        viewmodel.pullrequest.observe(this) {

            adapter = PullRequestAdapter(it)
            _binding.ltvPullRequest.adapter = adapter
        }

    }

    private fun initialSetup() {

        _binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setUpBar()

        _binding.ltvPullRequest.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpBar() {
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