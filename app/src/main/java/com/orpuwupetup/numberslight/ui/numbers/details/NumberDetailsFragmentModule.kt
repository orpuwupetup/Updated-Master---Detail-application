package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import dagger.Binds
import dagger.Module

@Module
abstract class NumberDetailsFragmentModule {

    @Binds
    @FragmentScoped
    internal abstract fun bindsNumberDetailsPresenter(presenter: NumberDetailsFragmentPresenter): NumberDetailsFragmentContract.Presenter
}