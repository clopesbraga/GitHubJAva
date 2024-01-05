package com.testemobile.githubjava.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Holder.ListHolder
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.View.PullRequestActivity
import com.testemobile.githubjava.databinding.RowOfReposBinding

class ListAdpter(itemsList: List<ItemsModel>):RecyclerView.Adapter<ListHolder>(){

    private var repoList: List<ItemsModel> = itemsList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {

        val item = RowOfReposBinding.inflate(LayoutInflater.from
                                            (parent.context),parent,false)

        return ListHolder(item)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ListHolder, position: Int) {

        holder.bind(repoList[position])

        holder.cardRowRepo.setOnClickListener(View.OnClickListener {

            Log.d("Clique","Card_selecionado")

            val intent = Intent(holder.itemView.context, PullRequestActivity::class.java)

                intent.putExtra("criador", repoList[position].owner?.login)
                intent.putExtra("repositorio", repoList[position].nomeRepositorio)

            holder.itemView.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {

        return repoList.count()
    }

    fun atualizaListaRepositorio(list: List<ItemsModel>){

        repoList = list
        notifyDataSetChanged()
    }

}