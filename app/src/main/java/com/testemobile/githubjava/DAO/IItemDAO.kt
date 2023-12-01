package com.testemobile.githubjava.DAO

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query
import androidx.room.Update
import com.testemobile.githubjava.Model.ItemsModelRepo

@Dao
interface IItemDAO{

    @Insert
    fun save(nome:ItemsModelRepo):Long

    @Update
    fun update(id: ItemsModelRepo):Int

    @Query(value="Select * from Items where id =:id")
    fun get(id:Int): ItemsModelRepo

    @Query(value="Select * from Items")
    fun listAll(): ItemsModelRepo
}