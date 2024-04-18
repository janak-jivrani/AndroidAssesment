package com.zw.zwbase.core

import android.content.Context
import javax.inject.Inject

class SharePref @Inject constructor(context: Context) {
    private val SHARED_PREF_NAME = "PREF_CHAT_APP"
    private val AUTH_TOKEN = "AUTH_TOKEN"
    private val IS_LOGINED = "IS_LOGINED"
    private val USER_ID = "USER_ID"
    private val NAME = "NAME"
    private val EMAIL = "EMAIL"
    private val USER_ROLE = "USER_ROLE"
    private val PASSWORD = "PASSWORD"
    private val NUMBER = "NUMBER"
    private val COUNTRY_CODE = "COUNTRY_CODE"
    private val COUNTRY_CODE_NAME = "COUNTRY_CODE_NAME"
    private val BIRTH_DATE = "BIRTH_DATE"
    private val PROFILE_IMAGE = "PROFILE_IMAGE"
    private val FCM_TOKEN = "FCM_TOKEN"
    val ALREADY_CHAT_GETTING = "ALREADY_CHAT_GETTING"
    val WORKER_MESSAGE_TIME = "WORKER_MESSAGE_TIME"

    private val sharedPreference =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val sharedPrefEditor = sharedPreference.edit()

    var isLogined
        get() = sharedPreference.getBoolean(IS_LOGINED, false)
        set(value) = sharedPrefEditor.putBoolean(IS_LOGINED, value).apply()

    var authToken: String?
        get() = sharedPreference.getString(AUTH_TOKEN, "")
        set(value) = sharedPrefEditor.putString(AUTH_TOKEN, value).apply()

    var name: String?
        get() = sharedPreference.getString(NAME, "")
        set(value) = sharedPrefEditor.putString(NAME, value).apply()

    var email: String?
        get() = sharedPreference.getString(EMAIL, "")
        set(value) = sharedPrefEditor.putString(EMAIL, value).apply()

    var userId: Int?
        get() = sharedPreference.getInt(USER_ID, 0)
        set(value) = sharedPrefEditor.putInt(USER_ID, value ?: 0).apply()

    var userRole: String?
        get() = sharedPreference.getString(USER_ROLE, "")
        set(value) = sharedPrefEditor.putString(USER_ROLE, value).apply()

    var countryCode: String?
        get() = sharedPreference.getString(COUNTRY_CODE, "")
        set(value) = sharedPrefEditor.putString(COUNTRY_CODE, value).apply()

    var countryCodeName: String?
        get() = sharedPreference.getString(COUNTRY_CODE_NAME, "")
        set(value) = sharedPrefEditor.putString(COUNTRY_CODE_NAME, value).apply()

    var number: String?
        get() = sharedPreference.getString(NUMBER, "")
        set(value) = sharedPrefEditor.putString(NUMBER, value).apply()

    var birthDate: String?
        get() = sharedPreference.getString(BIRTH_DATE, "")
        set(value) = sharedPrefEditor.putString(BIRTH_DATE, value).apply()

    var profileImage: String?
        get() = sharedPreference.getString(PROFILE_IMAGE, "")
        set(value) = sharedPrefEditor.putString(PROFILE_IMAGE, value).apply()

    var fcmToken: String?
        get() = sharedPreference.getString(FCM_TOKEN, "")
        set(value) = sharedPrefEditor.putString(FCM_TOKEN, value).apply()
    
    
    fun setIntSharedPref(key: String?, value: Int?) {
        sharedPrefEditor.putInt(key, value!!)
        sharedPrefEditor.commit()
    }

    fun setLongSharedPref(key: String?, value: Long) {
        sharedPrefEditor.putLong(key, value)
        sharedPrefEditor.commit()
    }

    fun setStringSharedPref(key: String?, value: String?) {
        sharedPrefEditor.putString(key, value)
        sharedPrefEditor.commit()
    }

    fun setBooleanPref(key: String?, value: Boolean) {
        sharedPrefEditor.putBoolean(key, value)
        sharedPrefEditor.commit()
    }

    fun getIntSharedPref(key: String?, value: Int): Int {
        return sharedPreference.getInt(key, value)
    }

    fun getLongSharedPref(key: String?, value: Long): Long {
        return sharedPreference.getLong(key, value)
    }

    fun getStringSharedPref(key: String?, value: String?): String? {
        return sharedPreference.getString(key, value)
    }

    fun getBooleanSharedPref(key: String?, value: Boolean): Boolean {
        return sharedPreference.getBoolean(key, value)
    }

    fun saveLoginData(
        id: Int?,
        name: String?,
        email: String?,
        role: String?,
        countryCode: String?,
        countryCodeName: String?,
        number: String?,
        birthDate: String?,
        profileImage: String?,
    ) {
        this.userId = id
        this.name = name
        this.email = email
        this.userRole = role
        this.number = number
        this.countryCode = countryCode
        this.countryCodeName = countryCodeName
        this.birthDate = birthDate
        this.profileImage = profileImage
    }

    fun clearSharePref() {
        sharedPrefEditor.clear().commit()
    }

}