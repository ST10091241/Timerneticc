package com.example.timernetic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var proceedbtn:ImageView
    private lateinit var emailtxt: EditText
    private lateinit var passwordtxt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        proceedbtn = findViewById(R.id.proceedbtn)
        emailtxt = findViewById(R.id.emailtxt)
        passwordtxt = findViewById(R.id.passwordtxt)

       proceedbtn.setOnClickListener {
            val email =emailtxt.text.toString().trim()
            val pass = passwordtxt.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(applicationContext, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}