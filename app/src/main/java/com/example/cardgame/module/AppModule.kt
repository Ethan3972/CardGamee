package com.example.cardgame

import com.example.cardgame.vm.BaseViewModel
import com.example.cardgame.vm.MainViewModel
import com.example.cardgame.vm.PlayGameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 *
 * @author Ethan on 2021-07-13.
 */

val appContext = module {
    single(named("appContext")) { androidContext() }
}

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel() }
    viewModel { PlayGameViewModel() }
}
