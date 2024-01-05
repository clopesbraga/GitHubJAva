package com.testemobile.githubjava.Holder

import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.testemobile.githubjava.Model.PullRequestModel
import com.testemobile.githubjava.databinding.RowOfPullRequestItemBinding

class PullRequestHolder(private val bind : RowOfPullRequestItemBinding): RecyclerView.ViewHolder(bind.root)  {


    fun bind(pullrequest : PullRequestModel){

        bind.tituloPullRequestsRecyclerView.text= pullrequest.tituloPullRequests
        bind.nomeAutorRecyclerView.text = pullrequest.user.login
        bind.bodyPullRequestRecyclerView.text = pullrequest.body
        bind.dataRecyclerView.text = pullrequest.dataPullRequests

    }

}