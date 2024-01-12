package com.testemobile.githubjava.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.ListAdpter
import com.testemobile.githubjava.NetWork.RequestRepoEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentRepoListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class ListFragment : Fragment() {

    private lateinit var _binding: FragmentRepoListBinding
    private val binding get()= _binding
    lateinit  var adapter: ListAdpter
    private lateinit var viewmodel : RepositorioViewModel
    private var page : String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
        page = arguments?.getString("page")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoListBinding.inflate(inflater,container,false)
        binding.ltvList.layoutManager = LinearLayoutManager(context)

        chargeListOfRepo(page)

        return binding.root
    }

    fun chargeListOfRepo(pagina:String?){

        val remote= RetrofitService.createService(RequestRepoEndpoint::class.java)
        val response= remote.getItems(pagina)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                adapter= ListAdpter(it.items)
                binding.ltvList.adapter= adapter

            },{
                it.message?.let { Log.d("REPO_ERROR", it) }
                Toast.makeText(context, R.string.list_repositorios_error, Toast.LENGTH_LONG).show()
            })
    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    private fun observe(){

        viewmodel.items.observe(viewLifecycleOwner){

            adapter.atualizaListaRepositorio(it)
        }

    }
}



