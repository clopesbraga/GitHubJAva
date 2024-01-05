package com.testemobile.githubjava.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testemobile.githubjava.Holder.PullRequestHolder
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.databinding.RowOfPullRequestItemBinding

class PullRequestAdapter(pullRequestList: List<PullRequestModel?>): RecyclerView.Adapter<PullRequestHolder>() {

    private var repoList: List<PullRequestModel?> = pullRequestList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestHolder {

        val item = RowOfPullRequestItemBinding.inflate(
            LayoutInflater.from
            (parent.context),parent,false)

        return PullRequestHolder(item)
    }

    override fun onBindViewHolder(holder: PullRequestHolder, position: Int) {

        repoList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return repoList.count()
    }

    fun atualizaListaRepositorio(list: List<PullRequestModel>){

        repoList = list
        notifyDataSetChanged()
    }



}