package com.example.recipebook.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val PREFS_NAME = "profile_prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveProfileImageUri(uri: String) {
        prefs.edit().putString("profile_image_uri", uri).apply()
    }

    fun getProfileImageUri(): String? {
        return prefs.getString("profile_image_uri", null)
    }

    fun saveUserName(name: String) {
        prefs.edit().putString("user_name", name).apply()
    }

    fun getUserName(): String? {
        return prefs.getString("user_name", "GO GURL")
    }

    fun saveUserEmail(email: String) {
        prefs.edit().putString("user_email", email).apply()
    }

    fun getUserEmail(): String? {
        return prefs.getString("user_email", null)
    }

    fun saveUserPassword(password: String) {
        prefs.edit().putString("user_password", password).apply()
    }

    fun getUserPassword(): String? {
        return prefs.getString("user_password", null)
    }

    fun checkCredentials(email: String, password: String): Boolean {
        val savedEmail = getUserEmail()
        val savedPassword = getUserPassword()
        return savedEmail == email && savedPassword == password
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}