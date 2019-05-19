package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.di.annotations.ActivityScoped
import com.orpuwupetup.numberslight.ui.main.MainActivity
import com.orpuwupetup.numberslight.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {

@ActivityScoped
@ContributesAndroidInjector(modules = [MainActivityModule::class])
internal abstract fun bindMainActivity(): MainActivity

}