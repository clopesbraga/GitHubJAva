package com.testemobile.githubjava.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.Adapter.ListAdpter
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var _binding: FragmentListBinding
    private val binding get()= _binding
    private val adpater = ListAdpter()
    private lateinit var viewmodel : RepositorioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(RepositorioViewModel::class.java)


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

        viewmodel.listAllItems()

        return binding.root

    }

    override fun onResume() {
        super.onResume()

        observe()

    }

    private fun observe(){

        viewmodel.items.observe(viewLifecycleOwner){

            adpater.atualizaListaRepositorio(it)
        }

    }

}



