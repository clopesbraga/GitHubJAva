package com.testemobile.githubjava.module

import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{RepositorioViewModel()}
}

