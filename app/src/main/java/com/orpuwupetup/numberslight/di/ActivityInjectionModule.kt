package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import com.orpuwupetup.numberslight.ui.main.MainActivity
import com.orpuwupetup.numberslight.ui.main.MainActivityModule
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailActivity
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [NumberDetailActivityModule::class])
    internal abstract fun bindNumberDetailActivity(): NumberDetailActivity

}