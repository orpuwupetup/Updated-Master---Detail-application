package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NumberDetailsFragmentModule {

    @Binds
    @FragmentScoped
    internal abstract fun bindsNumberDetailsPresenter(presenter: NumberDetailsFragmentPresenter): NumberDetailsFragmentContract.Presenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScoped
        internal fun providePicasso() = Picasso.get()
    }
}