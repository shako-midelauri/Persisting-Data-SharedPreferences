package com.example.sharedpreferences.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharedpreferences.R
import com.example.sharedpreferences.presentation.CoursesViewModel
import com.example.sharedpreferences.ui.list.CoursesAdapter
import kotlinx.android.synthetic.main.activity_courses.*

class CoursesActivity : AppCompatActivity() {

    private val viewModel: CoursesViewModel by viewModels()
    private val adapter by lazy { CoursesAdapter() }
    private var nightModeActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        initCourseList()
        subscribeToData()
    }

    private fun initCourseList() {
        courseList.layoutManager = LinearLayoutManager(this)
        courseList.itemAnimator = DefaultItemAnimator()
        courseList.adapter = adapter
    }

    private fun subscribeToData() {
        viewModel.courses.observe(this) {
            adapter.setCourses(it)
        }
        viewModel.darkThemeEnabled.observe(this) { nightModeActive ->
            this.nightModeActive = nightModeActive

            val defaultMode = if (nightModeActive) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.dayNightMode) {
            viewModel.toggleNightMode()
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (nightModeActive) {
            menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.icn_night_mode)
        } else {
            menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.icn_light_mode)
        }
        return true
    }
}
