package com.testemobile.githubjava.Repository

import android.content.Context
import com.testemobile.githubjava.Model.RepositorioLocalModel

class GithubRepository(context: Context) {

    private val base = AppDataBase.getDatabase(context).ItemDAO()

    fun save (items: RepositorioLocalModel):Boolean{
        return base.save(items)>0
    }

     fun update(id: RepositorioLocalModel): Int {
       return base.update(id)
    }

         fun listAll(): List<RepositorioLocalModel> {
         return base.listAll()
    }

}