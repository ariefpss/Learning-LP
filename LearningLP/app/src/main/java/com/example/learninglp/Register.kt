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
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn = findViewById<View>(R.id.signUp) as Button
        registerBtn.setOnClickListener(View.OnClickListener {
                view -> registerUser()
        })

        haveAccount.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser () {
        val emailTxt = findViewById<View>(R.id.inptNewEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.inptNewPass) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error registering, try again later :)", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            Toast.makeText(this, "Error resgistering, try again later:|", Toast.LENGTH_LONG).show()
        }
    }
}
