package com.testemobile.githubjava.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
 class ItemsModelRepo (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0,

    @ColumnInfo(name="nomeRepositorio")
    var nomeRepositorio : String ="",

    @ColumnInfo(name="descricaoRepositorio")
    var descricaoRepositorio : String="",

    @ColumnInfo(name="nomeAutor")
    var nomeAutor :String="",

    @ColumnInfo(name="fotoAutor")
    var fotoAutor : String="",

    @ColumnInfo(name="numeroStars")
    var numeroStars : String="",

    @ColumnInfo(name="numeroForks")
    var numeroForks : String="",

//    @ColumnInfo(name="owner")
//    var owner : String=""
)
