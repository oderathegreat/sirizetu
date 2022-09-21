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
        var databaseRef = firebaseDatabase.getReference("messages").push()
        databaseRef.setValue("Doing some coding work")

        var house_owner = House("Karuna Place Est", "10th Street UN rd", "Kshs 8M","Makori Martin")

        //setting value for it to be accessible
        databaseRef.setValue(house_owner)

        //Reading data from our database
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                  var value = snapshot.value as HashMap<String, Any>
                  Log.d( "Value is ====>: ", value.toString() )

                //log a single value
                //Log.d("Single Value is ", value.get("address").toString())
                //change read and write rules otherwise you get this exception
                //sirizetu D/Error: DatabaseError: Permission denied

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error ", error.toString())

            }
        })


    }
     //saving objects in db
    data class House(var location:String, var address:String, var price:String, var owner:String)
}