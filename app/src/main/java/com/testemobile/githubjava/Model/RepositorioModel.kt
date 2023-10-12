package com.testemobile.githubjava.Model

import com.google.gson.annotations.SerializedName

class RepositorioModel {

    @SerializedName(value="id")
    var id :Int = 0

    @SerializedName(value="username")
    var username :String = ""

    @SerializedName(value="description")
    var description :String = ""

    @SerializedName(value="topics")
    var topics :String = ""

}