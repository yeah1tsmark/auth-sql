package com.example.authenticationsql_mark

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
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
    lateinit var db:SQLiteDatabase

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        editforloginemail = findViewById(R.id.edtforlogin)
        editforloginpass = findViewById(R.id.edtforcreateacc)
        buttonforlogin = findViewById(R.id.btnforlogin)
        buttonforcreateacc = findViewById(R.id.btnforcreateacc)

        db = openOrCreateDatabase("Emobilisdb", MODE_PRIVATE, null)


        buttonforlogin.setOnClickListener {
            // write a logic to check if user exists in db
            var username1 = editforloginemail.getText().toString().trim()
            var userpass = editforloginpass.getText().toString().trim()

            //Validation
            if(username1.isEmpty() || userpass.isEmpty()){
                Toast.makeText(this, "Cannot submit empty field!", Toast.LENGTH_SHORT).show()
            }
            else{
                val cursor = db.rawQuery("SELECT * FROM users WHERE baruapepe=? AND nenosiri=?",
                    arrayOf(arrayOf(editforloginemail, editforloginpass).toString())
                )

                if (cursor != null && cursor.moveToFirst()) {
                    // user is authenticated, start a new activity
                    val intent = Intent(this, dashboard::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or password, please try again", Toast.LENGTH_SHORT).show()
                }

            }

        }

        buttonforcreateacc.setOnClickListener {
        var gotoregister = Intent(
            this, MainActivity::class.java)
            startActivity(gotoregister)
        }




    }
}