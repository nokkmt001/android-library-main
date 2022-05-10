package com.lediya.dagger2.api

import com.lediya.dagger2.data.CountryListResponse
import retrofit2.Response
import retrofit2.http.GET

//A retrofit Network Interface for the Api
interface M2PApi{

    @GET("facts.json")
    suspend fun getList(): Response<CountryListResponse>
}