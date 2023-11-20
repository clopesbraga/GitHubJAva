package com.testemobile.githubjava.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.testemobile.githubjava.Model.ItemsModel

@Dao
interface ItemDAO{
    @Insert
    fun save(usuario:ItemsModel):Long

    @Update
    fun update(id: ItemsModel):Int

}