package com.testemobile.githubjava.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.testemobile.githubjava.Model.PullRequestLocalModel
import com.testemobile.githubjava.Model.PullRequestModel

@Dao
interface IpullRequestDAO {

    @Insert
    fun save(nome: PullRequestLocalModel):Long

    @Update
    fun update(id: PullRequestLocalModel):Int

    @Query(value="Select * from Pullrequest where id =:id")
    fun get(id:Int): PullRequestLocalModel

    @Query(value="Select * from PullRequest")
    fun listAll():List<PullRequestLocalModel>

}