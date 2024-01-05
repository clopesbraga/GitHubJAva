package com.testemobile.githubjava.Utils

import android.widget.ImageView
import coil.load
import com.testemobile.githubjava.R


fun ImageView.CarregaImagem(url: String? = null){
    load(url) {
        fallback(R.drawable.avatar_icon)
        error(R.drawable.avatar_icon)
        placeholder(R.drawable.avatar_icon)
    }
}
