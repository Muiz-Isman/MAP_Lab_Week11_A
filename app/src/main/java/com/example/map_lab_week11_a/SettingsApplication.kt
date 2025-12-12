package com.example.map_lab_week11_a

import android.app.Application

class SettingsApplication : Application() {
    lateinit var settingsStore: SettingsStore

    override fun onCreate() {
        super.onCreate()
        settingsStore = SettingsStore(this)
    }
}