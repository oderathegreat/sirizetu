package com.example.sirizetu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages")
        databaseRef.setValue("My Good People mko vipi")

        var house_owner = House("Runda", "145 Street oyoo ln", "Kshs 15M","Oyoo tindo")

        //setting value for it to be accessible
        databaseRef.setValue(house_owner)

        //Reading data from our database
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                  var value = snapshot.value
                  //Log.d( "Value is ====>: ", value.toString() )
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error ", error.toString())

            }
        })


    }
     //saving objects in db
    data class House(var location:String, var address:String, var price:String, var owner:String)
}