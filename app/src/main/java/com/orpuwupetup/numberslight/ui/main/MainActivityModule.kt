package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class MainActivityModule {

    @Binds
    @ActivityScoped
    internal abstract fun bindMainPresenter(presenter: MainActivityPresenter): MainActivityContract.Presenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScoped
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }
}