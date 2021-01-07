package com.fadly.moviedb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText txtusername;
    EditText txtpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        pref = getSharedPreferences("login", MODE_PRIVATE);

        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        btnlogin = (Button)findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtusername.getText().toString().isEmpty()
                        ||txtpassword.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Fill Username / Password !"
                            ,Toast.LENGTH_SHORT).show();
                }
                else {
                if(txtusername.getText().toString().equalsIgnoreCase("fadly")
                        &&txtpassword.getText().toString().equalsIgnoreCase("fadly"))
                {
                    editor = pref.edit();
                    editor.putString("username",txtusername.getText().toString());
                    editor.putString("status", "login");
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), com.fadly.moviedb.MainMenu.class));
                    finish();
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "Wrong Password / Username !",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}