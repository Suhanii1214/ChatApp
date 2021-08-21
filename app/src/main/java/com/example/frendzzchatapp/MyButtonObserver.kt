package com.example.frendzzchatapp

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView

class MyButtonObserver(private val button: ImageView): TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, after: Int, count: Int) {
        if(s.toString().trim().isNotEmpty()) {
            button.isEnabled = true
            button.setImageResource(R.drawable.outline_send_24)
        } else {
            button.isEnabled = false
            button.setImageResource((R.drawable.outline_send_gray_24))
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {}
}