package com.testemobile.githubjava.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.testemobile.githubjava.View.Adapter.ListAdpter
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import com.testemobile.githubjava.databinding.FragmentRepoListBinding
import org.koin.android.ext.android.inject


class ListFragment : Fragment() {

    private lateinit var _binding: FragmentRepoListBinding
    private val binding get() = _binding
    private lateinit var adapter: ListAdpter
    private val viewmodel: RepositorioViewModel by inject()
    private var page: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        page = arguments?.getString("page")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        binding.ltvList.layoutManager = LinearLayoutManager(context)

        page?.let { viewmodel.populateListInUI(it) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        observe()

    }


    private fun observe() {

        viewmodel.items.observe(viewLifecycleOwner) {
            adapter.atualizaListaRepositorio(it)
        }

        viewmodel.gitHubRepo.observe(viewLifecycleOwner) {
            adapter = ListAdpter(it.items)
            binding.ltvList.adapter = adapter
        }

    }
}



