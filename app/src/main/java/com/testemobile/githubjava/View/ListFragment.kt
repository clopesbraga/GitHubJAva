package com.testemobile.githubjava.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.ListAdpter
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Retrofit.RequestRepoEndpoint
import com.testemobile.githubjava.Retrofit.RetrofitService
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment() {

    private lateinit var _binding: FragmentListBinding
    private val binding get()= _binding
    private lateinit  var adapter: ListAdpter
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

        _binding = FragmentListBinding.inflate(inflater,container,false)

        //CHAMA RECYCLERVIEW DOS PRODUTOS
        binding.ltvList.layoutManager = LinearLayoutManager(context)

        val remote= RetrofitService.createService(RequestRepoEndpoint::class.java)
        val call: Call<GitHubRepo> = remote.getItems(page.toString())
        val response = call.enqueue(object : Callback<GitHubRepo> {

            override fun onResponse(
                call: Call<GitHubRepo>,
                response: Response<GitHubRepo>
            ) {

                adapter= ListAdpter(response.body()!!.items)

                //CHAMA ADAPTER DOS PRODUTOS
                binding.ltvList.adapter= adapter
            }

            override fun onFailure(call: Call<GitHubRepo>, t:Throwable){
                val s = t.message
            }
        })


//        viewmodel.listAllLocalItems()

        return binding.root

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



