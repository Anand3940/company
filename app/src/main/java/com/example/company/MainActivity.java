package com.example.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.company.ui.Otp_Authentication;
import com.example.company.ui.phoneNumber;

public class MainActivity extends AppCompatActivity {
CheckBox checkBox;
Button createaccount;
TextView forgottextview;
EditText passwordedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        createaccount=(Button)findViewById(R.id.createaccountbtn);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,createaccount.class);
                startActivity(i);
            }
        });
        forgottextview=(TextView)findViewById(R.id.forgottextview);
        passwordedittext =(EditText)findViewById(R.id.Password);
        forgottextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, phoneNumber.class);
                startActivity(i);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    passwordedittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    passwordedittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}