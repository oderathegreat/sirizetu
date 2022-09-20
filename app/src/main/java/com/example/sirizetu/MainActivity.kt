package com.example.sirizetu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
      private lateinit var email_Input:EditText
      private lateinit var password_Input:EditText
      private lateinit var btn_CreateUser:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declare our variables

        email_Input = findViewById(R.id.edtEmail)
        password_Input = findViewById(R.id.edtPassword)
        btn_CreateUser = findViewById(R.id.btnCreateUser)

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()






       //on click when user presses button
        btn_CreateUser.setOnClickListener {


        }



    }
     //saving objects in db
    //data class House(var location:String, var address:String, var price:String, var owner:String)
}