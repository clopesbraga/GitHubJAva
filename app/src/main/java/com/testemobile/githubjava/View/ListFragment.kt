package com.testemobile.githubjava.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.View.Adapter.ListAdpter
import com.testemobile.githubjava.NetWork.RequestRepoEndpoint
import com.testemobile.githubjava.NetWork.RetrofitService
import com.testemobile.githubjava.R
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentRepoListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject


class ListFragment : Fragment() {

    private lateinit var _binding: FragmentRepoListBinding
    private val binding get()= _binding
    private lateinit  var adapter: ListAdpter
    private val viewmodel : RepositorioViewModel  by  inject()
    private var page : String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        var operation =false
        val remote= RetrofitService.createService(RequestRepoEndpoint::class.java)
        val response= remote.getItems(pagina)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                adapter= ListAdpter(it.items)
                viewmodel.sendToLocalData(it.items)
                binding.ltvList.adapter= adapter

            }) {
                it.message?.let { Log.d(R.string.repository_error.toString(),it) }
                Toast.makeText(context, R.string.list_repositorios_error, Toast.LENGTH_LONG).show()


            }
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



