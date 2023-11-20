package com.testemobile.githubjava.Repository

import android.content.Context
import com.testemobile.githubjava.Model.ItemsModel

class GithubRepository(context: Context)  {


    private val base = RepoDataBase.getDatabase(context).ItemDAO()

    fun save (usuario:ItemsModel):Boolean{
        return base.save(usuario)>0
    }

}