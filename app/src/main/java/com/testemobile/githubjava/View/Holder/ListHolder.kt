package com.testemobile.githubjava.View.Holder

import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Model.ItemsModel
import com.testemobile.githubjava.Utils.CarregaImagem
import com.testemobile.githubjava.databinding.RowOfReposBinding

class ListHolder(val bind: RowOfReposBinding):RecyclerView.ViewHolder(bind.root) {

  val cardRowRepo=bind.cardRowRepo

  fun bind(repo: ItemsModel){

    bind.txtNomeAutor.text = repo.owner?.login
    bind.txtreponome.text = repo.nomeRepositorio
    bind.txtrepodesc.text = repo.descricaoRepositorio
    bind.txtNumForks.text = repo.numeroForks
    bind.txtNumStars.text = repo.numeroStars
    bind.imgAutor.CarregaImagem(repo.owner?.avatarUrl)

  }

}