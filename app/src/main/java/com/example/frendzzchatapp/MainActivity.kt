package com.example.frendzzchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_signup.setOnClickListener {
            val signupIntent = Intent(this,SignUpActivity::class.java)
            startActivity(signupIntent)
        }

        button_login.setOnClickListener {
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}