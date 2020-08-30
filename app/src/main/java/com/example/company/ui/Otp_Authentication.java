package com.example.company.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.company.R;
import com.example.company.newpassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Authentication extends AppCompatActivity {
    EditText editText;
    Button btn;
    String phoneno;
    String Validation;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__authentication);
        editText=(EditText)findViewById(R.id.pp);
        btn=(Button)findViewById(R.id.buttonverify);
        phoneno=getIntent().getStringExtra("phoneno").toString();
        sendVerification();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code= phoneno.toString();
                if(code.isEmpty()||code.length()<6)
                {
                    return ;
                }
                verifyCode(code);
            }
        });
    }
    private void sendVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneno,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Validation = s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Otp_Authentication.this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private void verifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(Validation,code);
        signincredentials(phoneAuthCredential);
    }

    private void signincredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                   openactivity();
                   finish();
                }
                else
                {
                 Toast.makeText(Otp_Authentication.this,"verification failure",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void openactivity() {
        Intent i=new Intent(this,newpassword.class);
        startActivity(i);
    }
}