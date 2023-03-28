package com.example.chickenmasala

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MasalaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}
