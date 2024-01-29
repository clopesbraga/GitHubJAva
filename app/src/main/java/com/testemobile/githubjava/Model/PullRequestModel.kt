package com.testemobile.githubjava.Model

import com.google.gson.annotations.SerializedName

data class PullRequestModel(


    @SerializedName("title")
    var tituloPullRequests: String,

    @SerializedName("created_at")
    var dataPullRequests: String,

    @SerializedName("body")
    var body: String,

    @SerializedName("user")
    val user: User,

)

data class User(
    @SerializedName("login")
    val login:String
)

