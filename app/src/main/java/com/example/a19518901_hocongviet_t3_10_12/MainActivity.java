package com.example.a19518901_hocongviet_t3_10_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnCreate;
    EditText edtEmail,edtPass;
    FirebaseAuth firebaseAuth;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate=findViewById(R.id.btnLoginNow);
        btnLogin=findViewById(R.id.btnLogin);
        edtPass=findViewById(R.id.edtPass);
        edtEmail=findViewById(R.id.edtGmail);
        firebaseAuth=FirebaseAuth.getInstance();
        btnCreate.setOnClickListener(view -> {
         //   Intent i=new Intent(this,RegisterActivity.class);
            startActivity(i);
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

    private void Login() {
        String email=edtEmail.getText().toString();
        String pass=edtPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DatabaseReference rf= FirebaseDatabase.getInstance().getReference("users").child(
                            FirebaseAuth.getInstance().getCurrentUser().getUid()).child("role");

                    edtPass.setText("");
                    Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}