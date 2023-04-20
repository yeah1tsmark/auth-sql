package com.example.authenticationsql_mark

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editname_1:EditText
    lateinit var editname_2:EditText
    lateinit var editemail:EditText
    lateinit var editpassword:EditText
    lateinit var regbutton:Button
    lateinit var loginbutton:Button
    lateinit var viewbutton:Button
    lateinit var deletebutton:Button
    lateinit var db:SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editname_1.findViewById<EditText>(R.id.edt_name_first)
        editname_2.findViewById<EditText>(R.id.edt_name_second )
        editemail.findViewById<EditText>(R.id.edt_email)
        editpassword.findViewById<EditText>(R.id.edt_password)
        regbutton.findViewById<Button>(R.id.btn_register)
        loginbutton.findViewById<Button>(R.id.btn_login)
        viewbutton.findViewById<Button>(R.id.btn_view)
        deletebutton.findViewById<Button>(R.id.btn_delete)

        // create db called Emobilis
        db = openOrCreateDatabase("Emobilisdb", Context.MODE_PRIVATE, null)

        // create a table called users in the db
        db.execSQL("CREATE TABLE IF NOT EXISTS users(frstname VARCHAR, scndname VARCHAR, baruapepe VARCHAR, nenosiri VARCHAR)")

        //set onclick listeners to buttons
        regbutton.setOnClickListener {
            var name1 = editname_1.text.toString().trim()
            var name2 = editname_2.text.toString().trim()
            var email = editemail.text.toString().trim()
            var password = editpassword.text.toString().trim()

            // check if the user is submitting empty fields
            if (name1.isEmpty() || name2.isEmpty() || email.isEmpty() || password.isEmpty()){
                // use message function to display error message
                Toast.makeText(this, "Error!Please fill all inputs", Toast.LENGTH_SHORT).show()
            }else{
                // proceed to save data
                db.execSQL("INSERT INTO users VALUES('"+name1+"','"+name2+"''"+email+"','"+password+"')")
                Toast.makeText(this, "SUCCESS!User saved successfully!!", Toast.LENGTH_SHORT).show()

                var gotologin = Intent(this, Loginactivity::class.java)
                startActivity(gotologin)
            }

            }
        viewbutton.setOnClickListener {
            var cursor = db.rawQuery("SELECT * FROM users", null)
            // Check if there's any record in the database
            if (cursor.count == 0){
                Toast.makeText(this, "NO RECORD!Sorry,no users were found", Toast.LENGTH_SHORT).show()
            }else{
                // Use a string buffer to append all users retrieved using a loop
                var buffer = StringBuffer()
                while (cursor.moveToNext()){
                    var retrievedname1 = cursor.getString(0)
                    var retrievedname2 = cursor.getString(1)
                    var retrievedemail = cursor.getString(2)
                    var retrievedpassword = cursor.getString(3)
                    buffer.append(retrievedname1+"\n")
                    buffer.append(retrievedname2+"\n")
                    buffer.append(retrievedemail+"\n\n")
                    buffer.append(retrievedpassword+"\n\n")
                }
                Toast.makeText(this, "SUERS", Toast.LENGTH_SHORT).show()
                // message("SUERS",buffer.toString())
            }
        }

        deletebutton.setOnClickListener {
            var edt_email = editemail.text.toString().trim()
            if (edt_email.isEmpty()){
                Toast.makeText(this, "Please fill the Email field", Toast.LENGTH_SHORT).show()
                //message("EMPTY FIELD", "Please fill the ID field")
            }else{
                var cursor = db.rawQuery("SELECT * FROM users WHERE baruapepe ='"+editemail+"'", null)
                if (cursor.count == 0){
                    Toast.makeText(this, "NO RECORD!Sorry,no user found!)", Toast.LENGTH_SHORT).show()
                    //message("NO RECORD!", "Sorry, no user found!!")
                }else{
                    db.execSQL("DELETE FROM users WHERE baruapepe ='"+editemail+"'")
                    Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_SHORT).show()
                    //message("SUCCESS", "User deleted successfully!!")
                }
            }
        }






    }





    }
