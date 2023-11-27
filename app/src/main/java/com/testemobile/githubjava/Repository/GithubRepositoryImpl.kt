package com.testemobile.githubjava.Repository

import android.content.Context
import com.testemobile.githubjava.Model.ItemsModel

class GithubRepositoryImpl(context: Context) {

    private val base = AppDataBase.getDatabase(context).ItemDAO()


     fun update(id: ItemsModelRepo): Int {
        TODO("Not yet implemented")
    }

     fun get(id: Int): ItemsModelRepo{
         return base.update(id)
    }

}