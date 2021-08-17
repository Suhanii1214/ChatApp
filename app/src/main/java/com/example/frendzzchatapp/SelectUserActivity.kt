package com.example.frendzzchatapp

import android.content.ClipData
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

import kotlinx.android.synthetic.main.activity_select_user.*
import kotlinx.android.synthetic.main.activity_sign_up.view.*
import kotlinx.android.synthetic.main.userrow_new_messages.view.*


class SelectUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)
        supportActionBar?.title = "Select User"

//        val adapter = GroupAdapter<GroupieViewHolder> ()
//        adapter.add(UserItem())
//
//        recyclerview_newmessage.adapter = adapter
        fetchUsers()
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if(user!=null){
                        adapter.add(UserItem(user as User))
                    }
                }

                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItem

                    val intent  = Intent(view.context, ChatLogActivity::class.java)
                    intent.putExtra(USER_K)

                }

                recyclerview_newmessage.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }
}

class UserItem(val user: User): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_username_newmessage.text = user.username
    }

    override fun getLayout(): Int {
        return R.layout.userrow_new_messages
    }
}
