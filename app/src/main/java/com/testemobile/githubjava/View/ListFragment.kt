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
import com.testemobile.githubjava.Retrofit.RequestRepoEndpoint
import com.testemobile.githubjava.Retrofit.RetrofitService
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentRepoListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        //CHAMA RECYCLERVIEW DOS PRODUTOS
        binding.ltvList.layoutManager = LinearLayoutManager(context)

        chargeListOfRepo(page)

//        viewmodel.listAllLocalItems()

        return binding.root

    }

    fun chargeListOfRepo(pagina:String?){

        val remote= RetrofitService.createService(RequestRepoEndpoint::class.java)
        val call: Call<GitHubRepo> = remote.getItems(pagina)
        call.enqueue(object : Callback<GitHubRepo> {

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



