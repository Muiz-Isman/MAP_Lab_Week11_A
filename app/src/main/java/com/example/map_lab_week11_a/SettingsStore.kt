package com.example.map_lab_week11_a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Membuat instance DataStore (Single source of truth)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsStore")

class SettingsStore(private val context: Context) {

    // Key untuk menyimpan data
    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }

    // Membaca data sebagai Flow (stream data)
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            // Kembalikan value yang disimpan, atau string kosong jika belum ada
            preferences[KEY_TEXT] ?: ""
        }

    // Menyimpan data (suspend function karena berjalan di background)
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }
}