package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.di.annotations.FragmentScoped
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailsFragment
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailsFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [NumberDetailsFragmentModule::class])
    internal abstract fun provideNumberDetailsFragment(): NumberDetailsFragment

}