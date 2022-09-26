package com.example.sirizetu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.sirizetu.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference

class SettingsActivity : AppCompatActivity() {

    private lateinit var user_name:TextView
    private lateinit var user_status:TextView
    private lateinit var user_image:ImageView
    private lateinit var changeStatusbtn:Button


    var mDatabase:DatabaseReference? = null
    var mCurrentuser: FirebaseUser? = null
    var storageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        user_name = findViewById(R.id.settings_Displayname)
        user_status = findViewById(R.id.settings_Status)
        user_image = findViewById(R.id.settings_profilePic)
        changeStatusbtn = findViewById(R.id.btnStatus)

        //Instantiate our firebase services
        mCurrentuser = FirebaseAuth.getInstance().currentUser
        //get user id
        var userId = mCurrentuser!!.uid

        mDatabase = FirebaseDatabase.getInstance().reference
            .child("Users")
            .child(userId)


        mDatabase!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               var displayUsername = snapshot.child("user_name").value
                var userStatus = snapshot.child("status").value
                var imagePhoto = snapshot.child("image").value

             //append data
                user_name.text = displayUsername.toString()
                user_status.text = userStatus.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        changeStatusbtn.setOnClickListener {
            var intent = Intent(this, UserStatusActivity::class.java)
            intent.putExtra("status", user_status.text.toString().trim())
            startActivity(intent)
        }




    }
}