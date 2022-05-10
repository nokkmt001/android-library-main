package com.lediya.dagger2.api

import com.lediya.dagger2.data.CountryListResponse

class CountryListRepository(private val api: M2PApi): BaseRepository() {
    /**
     * Request api call
     *@return  the result of country data */
    suspend fun getCountryData() : CountryListResponse? {
        return safeApiCall(
        call = { api.getList()},
        errorMessage = "Error Fetching Popular Movies"
        )
    }
}