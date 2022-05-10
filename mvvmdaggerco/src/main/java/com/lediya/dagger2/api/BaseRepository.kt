package com.lediya.dagger2.api

import android.util.Log
import com.lediya.dagger2.utils.Constants
import retrofit2.Response
import java.io.IOException

/**
 * Base repository
 */
open class BaseRepository {
    /**
     * Request api call
     * @param M2pApi and errorMessage
     * @return the result */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.e(Constants.TAG, "$errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }
    /**
     * Request api call
     * @param M2pApi and errorMessage
     * @return the result */

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T> {
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}