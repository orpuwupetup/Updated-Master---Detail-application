package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.NumbersLightApplication
import com.orpuwupetup.numberslight.api.di.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NumbersLightApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: NumbersLightApplication)
}