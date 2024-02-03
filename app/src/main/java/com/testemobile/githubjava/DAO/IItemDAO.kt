package com.testemobile.githubjava.DAO

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query
import androidx.room.Update
import com.testemobile.githubjava.Model.RepositorioLocalModel

@Dao
interface IItemDAO{

    @Insert
    fun save(nome:RepositorioLocalModel):Long

    @Update
    fun update(id: RepositorioLocalModel):Int

    @Query(value="Select * from Repositorios where id =:id")
    fun get(id:Int): Int

    @Query(value="Select * from Repositorios")
    fun listAll():List<RepositorioLocalModel>
}