package com.testemobile.githubjava.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Pullrequest")
 class PullRequestLocalModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int?=0,

    @ColumnInfo(name="titulo")
    var titulo: String?="",


    @ColumnInfo(name="data")
    var dataPullRequests: String?="",


    @ColumnInfo(name="body")
    var body: String?="",

    @SerializedName("user")
    var user: String?="",

)


