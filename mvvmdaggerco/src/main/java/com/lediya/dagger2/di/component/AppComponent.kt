package com.lediya.dagger2.di.component

import dagger.Component
import com.lediya.dagger2.di.module.CoroutineScopeModule
import com.lediya.dagger2.di.module.NetworkModule
import com.lediya.dagger2.view.viewModel.ListScreenViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (CoroutineScopeModule::class)])
interface AppComponent {

    fun inject(viewModel: ListScreenViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun coroutineScopeModule(coroutineScopeModule: CoroutineScopeModule): Builder
    }
}