package com.testemobile.githubjava.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Model.RepositorioModel
import com.testemobile.githubjava.databinding.UserListBinding

class ListHolder(private val bind: UserListBinding):RecyclerView.ViewHolder(bind.root) {

  fun bind(repo:RepositorioModel){

//    bind.txtreponome.text = repo.id.toString()
    bind.txtrepodesc.text = repo.username

  }

}