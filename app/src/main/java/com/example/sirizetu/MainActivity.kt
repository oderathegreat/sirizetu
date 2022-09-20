package com.example.sirizetu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
      private lateinit var email_Input:EditText
      private lateinit var password_Input:EditText
      private lateinit var btn_CreateUser:Button
      private  var mAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declare our variables
        email_Input = findViewById(R.id.edtEmail)
        password_Input = findViewById(R.id.edtPassword)
        btn_CreateUser = findViewById(R.id.btnCreateUser)

        //get our firebase Instance
        mAuth = FirebaseAuth.getInstance()

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()


       //on click when user presses button
        btn_CreateUser.setOnClickListener {

            val email = email_Input.text.toString().trim()
            val password = password_Input.text.toString().trim()

            if (email !=null && password !=null) {
                //proceed to create account
                mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {

                        Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Cannot Create Account", Toast.LENGTH_SHORT).show()
                    }
                }

            } else{
                Toast.makeText(this, "Cannot submit an empty input field", Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(this, "Email is $email and password is $password", Toast.LENGTH_SHORT).show()

        }



    }
     //saving objects in db
    //data class House(var location:String, var address:String, var price:String, var owner:String)
}