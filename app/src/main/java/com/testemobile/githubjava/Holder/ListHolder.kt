package com.testemobile.githubjava.Holder

import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.databinding.UserListBinding

class ListHolder(private val bind: UserListBinding):RecyclerView.ViewHolder(bind.root) {

  fun bind(repo:ItemsModel){

//    bind.txtreponome.text = repo.id.toString()
    bind.txtrepodesc.text = repo.nomeAutor

  }

}