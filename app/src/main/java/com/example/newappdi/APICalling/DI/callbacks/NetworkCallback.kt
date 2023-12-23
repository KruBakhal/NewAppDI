package com.example.newappdi.APICalling.DI.callbacks

/**
 * Created by Sandip Savaliya on 26/2/19 at 9:27 AM.
 * Coruscate Solutions.
 */

interface NetworkCallback<T> {
    fun onSuccessResponse(response: T)
    fun onErrorResponse(strErrorMessage: String, responseCode: String, isNetworkError: Boolean)
}
