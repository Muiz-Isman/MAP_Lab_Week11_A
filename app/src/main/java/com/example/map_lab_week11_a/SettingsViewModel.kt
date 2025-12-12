package com.example.map_lab_week11_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Collect data dari Flow (DataStore) dan update LiveData
        viewModelScope.launch {
            settingsStore.text.collect {
                _textLiveData.value = it
            }
        }
    }

    fun saveText(text: String) {
        // Simpan data via Coroutine
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}