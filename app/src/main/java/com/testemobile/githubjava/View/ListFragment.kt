package com.testemobile.githubjava.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.ListAdpter
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentListBinding
import org.koin.android.ext.android.inject


class ListFragment : Fragment() {

    private lateinit var _binding: FragmentListBinding
    private val binding get()= _binding
    private val adpater = ListAdpter(mutableListOf())
    private val viewModel : RepositorioViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentListBinding.inflate(inflater,container,false)

        //CHAMA RECYCLERVIEW DOS PRODUTOS
        binding.ltvList.layoutManager = LinearLayoutManager(context)

        //CHAMA ADAPTER DOS PRODUTOS
        binding.ltvList.adapter= adpater

        return binding.root

    }

    override fun onResume() {
        super.onResume()

        observe()

    }

    private fun observe(){

        viewModel.mitems.observe(viewLifecycleOwner){

            adpater.atualizaListaRepositorio(it)
        }

    }

}



