package com.example.mike.phonebook4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mike.phonebook4.mDataBase.DBAdapter2;

import net.sqlcipher.database.SQLiteDatabase;

public class Login extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    DBAdapter2 db;
    EditText editTextUserName,editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SQLiteDatabase.loadLibs(this);
        editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
        editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);
        // create a instance of SQLite Database
        db=new DBAdapter2(this);
        db.openDB2();

        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIn);
        btnSignUp=(Button)findViewById(R.id.buttonRegister);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),Register.class);
                startActivity(intentSignUP);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                EncDec encDec = new EncDec();
                try {
                    String a = encDec.encrypt(userName);
                    String b = encDec.encrypt(password);
                    String storedPassword = db.getSinlgeEntry(a);

                    // check if the Stored password matches with  Password entered by user
                    if (b.equals(storedPassword)) {
                        Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // fetch the Password form database for respective user name


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        db.closeDB2();
    }
    
     @Override
    public void onBackPressed() {
        this.finishAffinity();
        super.onBackPressed();
    }
}

