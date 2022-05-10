package com.lediya.dagger2.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Module which provides all required dependencies about kotlin coroutines
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object CoroutineScopeModule {


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCoroutineScope(coroutineContext: CoroutineContext): CoroutineScope{
        return CoroutineScope(coroutineContext)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCoroutineContext(): CoroutineContext{
        val parentJob = Job()
        val coroutineContext = parentJob + Dispatchers.IO
        return coroutineContext
    }
}