package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragmentModule
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragment
import com.orpuwupetup.numberslight.ui.numbers.list.NumbersListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [NumberDetailsFragmentModule::class])
    internal abstract fun provideNumberDetailsFragment(): NumberDetailsFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [NumbersListFragmentModule::class])
    internal abstract fun provideNumbersListFragment(): NumbersListFragment

}