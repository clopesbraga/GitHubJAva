package com.testemobile.githubjava.Holder

import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Model.ItemsModelRepo
import com.testemobile.githubjava.Utils.CarregaImagem
import com.testemobile.githubjava.databinding.RowUserRepoBinding

class ListHolder(private val bind: RowUserRepoBinding):RecyclerView.ViewHolder(bind.root) {

  fun bind(repo: ItemsModel){

    bind.txtNomeAutor.text = repo.owner?.login
    bind.txtreponome.text = repo.nomeRepositorio
    bind.txtrepodesc.text = repo.descricaoRepositorio
    bind.txtNumForks.text = repo.numeroForks
    bind.txtNumStars.text = repo.numeroStars
    bind.imgAutor.CarregaImagem(repo.owner?.avatarUrl)

  }

}