package com.example.learninglp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val masukBtn = findViewById<View>(R.id.signIn) as Button
        masukBtn.setOnClickListener(View.OnClickListener {
                view -> masukUser()
        })
    }

    private fun masukUser() {
        val emailTxt = findViewById<View>(R.id.inptEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.inptPass) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        val intent = Intent(this, PageHome::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Error Login", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            Toast.makeText(this, "Error Login :|", Toast.LENGTH_LONG).show()
        }
    }

}
