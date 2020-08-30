package com.example.company.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.company.CheckInternet;
import com.example.company.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class phoneNumber extends AppCompatActivity {
    EditText editphone;
    String code;
    Button btn;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        editphone=(EditText)findViewById(R.id.phone);
        btn=(Button)findViewById(R.id.buttonverify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              verifyphonenumber(editphone);
            }
        });
    }
    public void verifyphonenumber(final EditText editphone)
    {
        phone="+91"+editphone.getText().toString().trim();
        CheckInternet check=new CheckInternet();
        if(!check.isconnected(this));
        Query checkuser= FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneno").equalTo(phone);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    editphone.setError(null);
                    Intent i=new Intent(phoneNumber.this,Otp_Authentication.class);
                    String aa=editphone.getText().toString().trim();
                    i.putExtra("phoneno",aa);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Enter Number is not registered",Toast.LENGTH_LONG).show();
            }
        });
    }

}