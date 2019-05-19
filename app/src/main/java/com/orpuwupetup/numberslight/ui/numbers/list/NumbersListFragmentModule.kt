package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import dagger.Binds
import dagger.Module

@Module
abstract class NumbersListFragmentModule {

    @Binds
    internal abstract fun bindsNumberDetailsPresenter(presenter: NumbersListFragmentPresenter): NumbersListFragmentContract.Presenter
}