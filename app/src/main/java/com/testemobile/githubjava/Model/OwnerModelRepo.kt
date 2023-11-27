package com.testemobile.githubjava.Model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "owner")
 class OwnerModelRepo {

    @ColumnInfo(name="login")
    var login: String=""

    @ColumnInfo(name="avatarUrl")
    val avatarUrl: String=""
}