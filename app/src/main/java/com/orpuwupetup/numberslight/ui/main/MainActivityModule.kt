package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    @ActivityScoped
    internal abstract fun bindMainPresenter(presenter: MainActivityPresenter): MainActivityContract.Presenter
}