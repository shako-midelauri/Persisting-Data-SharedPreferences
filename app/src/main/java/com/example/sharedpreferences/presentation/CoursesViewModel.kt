package com.example.sharedpreferences.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sharedpreferences.data.getCourseList
import com.example.sharedpreferences.data.preferences.SharedPrefs
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {

    private val sharedPrefs = SharedPrefs()

    val darkThemeEnabled = MutableLiveData<Boolean>()
    val courses = getCourseList().asLiveData()

    init {
        darkThemeEnabled.value = sharedPrefs.isDarkThemeEnabled()
    }

    fun toggleNightMode() {
        viewModelScope.launch {
            sharedPrefs.setDarkThemeEnabled(!darkThemeEnabled.value!!)
            darkThemeEnabled.value = !darkThemeEnabled.value!!
        }
    }
}