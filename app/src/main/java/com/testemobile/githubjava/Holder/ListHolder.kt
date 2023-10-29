package com.testemobile.githubjava.Holder

import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.databinding.RowUserRepoBinding

class ListHolder(private val bind: RowUserRepoBinding):RecyclerView.ViewHolder(bind.root) {

  fun bind(repo:ItemsModel){

    bind.txtreponome.text = repo.nomeAutor
    bind.txtrepodesc.text = repo.descricaoRepositorio

  }

}