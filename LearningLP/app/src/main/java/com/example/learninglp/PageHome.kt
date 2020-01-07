package com.example.learninglp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_page_home.*

class PageHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)

        backend.setOnClickListener {
            val intent = Intent(this, Backend::class.java)
            startActivity(intent)
        }

        frontend.setOnClickListener {
            val intent = Intent(this, Frontend::class.java)
            startActivity(intent)
        }
    }
}
