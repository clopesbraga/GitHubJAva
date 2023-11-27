package com.testemobile.githubjava.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Holder.ListHolder
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.databinding.RowUserRepoBinding

class ListAdpter(private var repoList:List <ItemsModel> )
                                    :RecyclerView.Adapter<ListHolder>(){

//    private var repoList: List<ItemsModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {

        val item = RowUserRepoBinding.inflate(LayoutInflater.from
                                            (parent.context),parent,false)

        return ListHolder(item)

    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {

        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {

        return repoList.count()
    }

    fun atualizaListaRepositorio(list: ItemsModel){

        repoList = listOf(list)
        notifyDataSetChanged()

    }



}