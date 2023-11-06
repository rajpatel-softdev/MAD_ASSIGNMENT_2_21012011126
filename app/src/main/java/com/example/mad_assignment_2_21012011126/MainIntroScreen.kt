package com.example.mad_assignment_2_21012011126

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainIntroScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_intro_screen)

        val introBtn: Button = findViewById(R.id.button)
        introBtn.setOnClickListener{
            val intent = Intent(this, finalScreen::class.java)
            startActivity(intent)
        }

    }
}