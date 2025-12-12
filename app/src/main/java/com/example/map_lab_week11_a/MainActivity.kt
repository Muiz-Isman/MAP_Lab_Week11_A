package com.example.map_lab_week11_a


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Ambil instance settingsStore dari Application
        val settingsStore = (application as SettingsApplication).settingsStore

        // 2. Setup ViewModel dengan Factory (karena ViewModel butuh parameter)
        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(settingsStore) as T
            }
        })[SettingsViewModel::class.java]

        // 3. Observe LiveData untuk update UI otomatis
        viewModel.textLiveData.observe(this) { savedText ->
            findViewById<TextView>(R.id.activity_main_text_view).text = savedText
        }

        // 4. Logic Tombol Save
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val inputText = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            viewModel.saveText(inputText)
        }
    }
}