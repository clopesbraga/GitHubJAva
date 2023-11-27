package com.testemobile.githubjava.Model

import com.google.gson.annotations.SerializedName

data class GitHubRepo(

    @SerializedName("total_count")
    var totalCont: Long,
    @SerializedName("items")
    var items: MutableList<ItemsModel>,

    )

data class ItemsModel(
    @SerializedName("name")
    val nomeRepositorio: String? = null,
    @SerializedName("description")
    val descricaoRepositorio: String? = null,
    @SerializedName("login")
    val nomeAutor: String? = null,
    @SerializedName("avatar_url")
    val fotoAutor: String? = null,
    @SerializedName("stargazers_count")
    val numeroStars: String? = null,
    @SerializedName("forks_count")
    val numeroForks: String? = null,

    @SerializedName("owner")
    val owner: OwnerModel?
)

data class OwnerModel(

    @SerializedName("login")
    val login: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String?

)