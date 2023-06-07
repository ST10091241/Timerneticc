package com.example.timernetic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.EditText
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

    }
}