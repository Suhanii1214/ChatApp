package com.example.frendzzchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_loginpage.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email = edit_textview_email_login.text.toString()
        val password = edit_textview_password_login.text.toString()

        if(email.equals(null) || password.equals(null)) {
            Toast.makeText(this,"Please enter all the fields",Toast.LENGTH_LONG).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful)
                        return@addOnCompleteListener

                    Log.d("LoginActivity", "User succesfully logged in with uid ${it.result?.user?.uid}")
                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
}