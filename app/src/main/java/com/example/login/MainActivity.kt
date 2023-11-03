package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<TextInputEditText>(R.id.name)
        val password = findViewById<TextInputEditText>(R.id.password)
        val subBtn = findViewById<Button>(R.id.subBtn)

        val namec = findViewById<TextInputEditText>(R.id.namec)
        val passc = findViewById<TextInputEditText>(R.id.passc)
        val logBtn = findViewById<Button>(R.id.logBtn)

        subBtn.setOnClickListener{
            val namet = name.text.toString()
            val passt = password.text.toString()

            val user = User(namet, passt)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(namet).setValue(user).addOnSuccessListener{
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            }
        }

        logBtn.setOnClickListener{
            val nametc = namec.text.toString()
            val passtc = passc.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(nametc).get().addOnSuccessListener {
                if(it.exists())
                {
                    val crctpas = it.child("password").value
                    if(crctpas == passtc)
                    {
                        Toast.makeText(this, "Login sucess", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, "unsucessful", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}