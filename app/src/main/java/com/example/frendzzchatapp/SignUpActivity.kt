package com.example.frendzzchatapp

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {

//    var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        selectphoto_button.setOnClickListener {
//            Log.d("SignUpActivity","Select a photo")
//
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent,0)
//        }
//
//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode,resultCode,data)
//
//            if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
//                Log.d("SignUpActivity","Photo was selected")
//
//                selectedPhotoUri = data.data
//                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)
//
//                selectphoto_imageview_signup.setImageBitmap(bitmap)
//                selectphoto_button.alpha = 0f
//            }
//        }

        button_signuppage.setOnClickListener {
            performSignUp()
        }
    }

    private fun performSignUp() {
        val email: String = edit_textview_email_signup.text.toString()
        val password = edit_textview_password_signup.text.toString()
        val username = edit_textview_username_signup.text.toString()
        val number = edit_textview_number_signup.text.toString()

        if(email.equals(null)||password.equals(null)||username.equals(null)||number.equals(null)) {
            Toast.makeText(this,"Please enter all the fields",Toast.LENGTH_LONG).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful)
                        return@addOnCompleteListener
                    else
                        Log.d("SignActivity","User successfully created with uid ${it.result?.user?.uid}")
//                        uploadImagetoFirebase()
                }
                .addOnFailureListener {
                    Log.d("SignActivity","Failed to create user account ${it.message}")
                    Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }

        val intent  = Intent(this,LatestMessagesActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

//    private fun uploadImagetoFirebase() {
//        if(selectedPhotoUri == null) return
//
//        val filename = UUID.randomUUID().toString()
//        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
//
//        ref.putFile(selectedPhotoUri!!)
//            .addOnSuccessListener {
//                Log.d("SignUpActivity","Succesfully uploaded the image: ${it.metadata?.path}")
//
//                ref.downloadUrl.addOnSuccessListener {
//                    Log.d("SignUpActivity","File location $it")
//                    saveUserToFirebase(it.toString())
//                }
//            }
//            .addOnFailureListener {
//                //do some logging
//            }
//    }
//
//    private fun saveUserToFirebase(profileImageUrl: String) {
//        val uid = FirebaseAuth.getInstance().uid?:""
//        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
//
//        val user = User(uid,edit_textview_username_signup.text.toString(),profileImageUrl)
//
//        ref.setValue(user).addOnSuccessListener {
//            Log.d("SignUpActivity","Finally we saved the user to Firebase Database")
//        }
//    }
}
