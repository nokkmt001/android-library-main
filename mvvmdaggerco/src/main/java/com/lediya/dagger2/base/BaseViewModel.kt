package com.lediya.dagger2.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lediya.dagger2.di.component.AppComponent
import com.lediya.dagger2.di.component.DaggerAppComponent
import com.lediya.dagger2.view.viewModel.ListScreenViewModel


abstract class BaseViewModel(application: Application): AndroidViewModel(application){
    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ListScreenViewModel -> injector.inject(this)
        }
    }
}