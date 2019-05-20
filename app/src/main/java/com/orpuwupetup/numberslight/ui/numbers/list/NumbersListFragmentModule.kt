package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import com.orpuwupetup.numberslight.ui.numbers.list.adapter.NumbersAdapter
import com.orpuwupetup.numberslight.utils.network.NetworkConnectionChecker
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
abstract class NumbersListFragmentModule {

    @Binds
    @FragmentScoped
    internal abstract fun bindsNumberDetailsPresenter(presenter: NumbersListFragmentPresenter): NumbersListFragmentContract.Presenter

    @Module
    companion object {

        @FragmentScoped
        @JvmStatic
        @Provides
        internal fun provideNumbersAdapter(fragment: NumbersListFragment) =
                NumbersAdapter(fragment.requireContext(), mutableListOf(), PublishSubject.create(), Picasso.get())

        @FragmentScoped
        @JvmStatic
        @Provides
        internal fun provideConnectionChecker(fragment: NumbersListFragment) =
                NetworkConnectionChecker(fragment.requireContext())
    }
}