package com.testemobile.githubjava.Repository

import android.content.Context
import com.testemobile.githubjava.Model.PullRequestLocalModel

class PullRequestRepository(context : Context) {

    private val base = AppDataBase.getDatabase(context).IpullRequestDAO()

    fun save (pullrequests: PullRequestLocalModel):Boolean{
        return base.save(pullrequests)>0
    }

    fun update(id: PullRequestLocalModel): Int {
        return base.update(id)
    }

    fun listAll(): List<PullRequestLocalModel> {
        return base.listAll()
    }


}