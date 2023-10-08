package com.testemobile.githubjava.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Holder.ListHolder
import com.testemobile.githubjava.Model.RepositorioModel
import com.testemobile.githubjava.databinding.UserListBinding

class ListAdpter:RecyclerView.Adapter<ListHolder>(){

    private var repoList: List<RepositorioModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {

        val item = UserListBinding.inflate(LayoutInflater.from
                                            (parent.context),parent,false)

        return ListHolder(item)

    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {

        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {

        return repoList.count()
    }

    fun atualizaListaRepositorio(list : List<RepositorioModel>){

        repoList = list
        notifyDataSetChanged()

    }

}