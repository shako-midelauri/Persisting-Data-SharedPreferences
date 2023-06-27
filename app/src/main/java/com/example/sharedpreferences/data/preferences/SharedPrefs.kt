package com.example.sharedpreferences.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.sharedpreferences.App

class SharedPrefs {

    private val preferences: SharedPreferences =
        App.instance.applicationContext.getSharedPreferences(
            SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
        )

    fun setDarkThemeEnabled(enabled: Boolean) {
        preferences.edit()
            .putBoolean(KEY_DARK_THEME, enabled)
            .apply()
    }

    fun isDarkThemeEnabled(): Boolean {
        return preferences.getBoolean(KEY_DARK_THEME, false)
    }

    companion object {
        private const val KEY_DARK_THEME = "dark_theme_enabled"
        private const val SHARED_PREFERENCES_NAME = "app_theme"
    }
}