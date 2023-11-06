package com.example.mad_assignment_2_21012011126

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutClick:View=findViewById(R.id.forLayout)
        layoutClick.setOnClickListener{
            val intent=Intent(this, MainIntroScreen::class.java)
            startActivity(intent)
        }
    }
}