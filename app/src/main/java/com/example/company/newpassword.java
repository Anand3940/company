package com.example.company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newpassword extends AppCompatActivity {
    EditText password;
    String phoneno="";
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
        password=(EditText)findViewById(R.id.newpasswrodedit);
        update=(Button)findViewById(R.id.updatebutton);
        phoneno=getIntent().getStringExtra("phoneno");

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.child(phoneno).child("password").setValue(password);
finish();
    }
}