package com.orpuwupetup.numberslight.di

import com.orpuwupetup.numberslight.NumbersLightApplication
import com.orpuwupetup.numberslight.api.di.NetModule
import com.orpuwupetup.numberslight.data.di.NumbersRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityInjectionModule::class, AndroidSupportInjectionModule::class, FragmentInjectionModule::class,
        NetModule::class, NumbersRepositoryModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NumbersLightApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: NumbersLightApplication)
}