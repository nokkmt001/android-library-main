package com.lediya.dagger2.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lediya.dagger2.api.CountryListRepository
import com.lediya.dagger2.api.M2PApi
import com.lediya.dagger2.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
@Suppress("unused")
object NetworkModule {
    /**
     *
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMovieRepository(m2pApi: M2PApi): CountryListRepository {
        return CountryListRepository(m2pApi)
    }
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): M2PApi {
        return retrofit.create(M2PApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    /**
     * Provides the OkHttpClient object.
     * @return the OkHttpClient object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal  fun provideClient(authInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }


    /**
     * Provides the Interceptor object.
     * @return the Interceptor object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal  fun provideInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}