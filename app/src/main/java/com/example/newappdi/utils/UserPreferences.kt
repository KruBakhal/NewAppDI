@file:Suppress("PrivatePropertyName")

package com.example.newappdi.utils


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

//val userPrefModule = module {
//    single { UserPreferences(get()) }
//}

open class UserPreferences(var context: Context) {
    private val KEY_USER_DATA = "userData"
    private val KEY_USER_DATA_GAURDIAN = "guardian"
    private val KEY_AUTH_TOKEN = "authToken"
    private val KEY_REFRESH_TOKEN = "refreshToken"
    val sharedPreference = getSharedPreferences("UserPreferences")

    fun getSharedPreferences(preference: String): SharedPreferences {
        return context.getSharedPreferences(preference, MODE_PRIVATE)
    }

    var token: String? = null
        get() {
            return if (field == null) sharedPreference.getString(KEY_AUTH_TOKEN, null)
            else field
        }
        set(value) {
            field = value
            sharedPreference.edit(commit = true) { putString(KEY_AUTH_TOKEN, value) }
        }

    var refreshToken: String? = null
        get() {
            return if (field == null) sharedPreference.getString(KEY_REFRESH_TOKEN, null)
            else field
        }
        set(value) {
            field = value
            sharedPreference.edit(commit = true) { putString(KEY_REFRESH_TOKEN, value) }
        }
/*
    var loggedInUser: UserData?
        get() {
            sharedPreference.getString(KEY_USER_DATA, null)?.let {
                return Gson().fromJson(
                    it, UserData::class.java
                )
            }
            return null
        }
        set(value) {
            sharedPreference.edit(commit = true) { putString(KEY_USER_DATA, Gson().toJson(value)) }
        }
    var loggedInUserGaurdianData: GaurdianModel?
        get() {
            sharedPreference.getString(KEY_USER_DATA_GAURDIAN, null)?.let {
                return Gson().fromJson(
                    it, GaurdianModel::class.java
                )
            }
            return null
        }
        set(value) {
            sharedPreference.edit(commit = true) { putString(KEY_USER_DATA_GAURDIAN, Gson().toJson(value)) }
        }*/
}

