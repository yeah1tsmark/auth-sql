package com.example.authenticationsql_mark

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Loginactivity : AppCompatActivity() {
    lateinit var editforloginemail:EditText
    lateinit var editforloginpass:EditText
    lateinit var buttonforlogin:Button
    lateinit var buttonforcreateacc:Button

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        editforloginemail.findViewById<EditText>(R.id.edtforlogin)
        editforloginpass.findViewById<EditText>(R.id.edtforcreateacc)
        buttonforlogin.findViewById<Button>(R.id.btnforlogin)
        buttonforcreateacc.findViewById<Button>(R.id.btnforcreateacc)


        buttonforlogin.setOnClickListener {
            // write a logic to check if user exists in db
            var username1 = editforloginemail.getText().toString().trim()
            var userpass = editforloginpass.getText().toString().trim()

            //Validation
            if(username1.isEmpty() || userpass.isEmpty()){
                Toast.makeText(this, "Cannot submit empty field!", Toast.LENGTH_SHORT).show()
            }
            else{

            }

        }

        buttonforcreateacc.setOnClickListener {
        var gotoregister = Intent(
            this, MainActivity::class.java)
            startActivity(gotoregister)
        }




    }
}