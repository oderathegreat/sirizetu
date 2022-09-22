package com.example.sirizetu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var email_input:EditText
    private lateinit var phone_input:EditText
    private lateinit var username_input:EditText
    private lateinit var pass_input:EditText
    private lateinit var btn_create:Button
    private var mAuth:FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var firebasedatabase = FirebaseDatabase.getInstance()
        var datareference = firebasedatabase.reference
        //connecting to ui
        email_input = findViewById(R.id.myemailedt)
        phone_input = findViewById(R.id.myedtphone)
        username_input = findViewById(R.id.myusernamedet)
        pass_input = findViewById(R.id.mypassedt)
        btn_create = findViewById(R.id.btnCreateUser)


        btn_create.setOnClickListener {

           var email = email_input.text.toString().trim()
            var password = pass_input.text.toString().trim()

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task: Task<AuthResult> ->
                if (task.isSuccessful) {

                    Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this,"Failed to Create Account", Toast.LENGTH_SHORT).show()

                }

            }

        }







    }
}








