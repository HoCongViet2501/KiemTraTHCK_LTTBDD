package com.example.a19518901_hocongviet_t3_10_12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister,btnLoginNow;
    EditText edtUserName,edtEmail,edtPass,edtConfirm;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        btnRegister=findViewById(R.id.btnLogin);
        btnLoginNow=findViewById(R.id.btnLoginNow);
        edtConfirm=findViewById(R.id.edtConfirm);
        edtEmail=findViewById(R.id.edtGmail);
        edtPass=findViewById(R.id.edtPassWord);
        edtUserName=findViewById(R.id.edtUserName);
        firebaseAuth=FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(view -> {
            if(edtUserName.getText().length()==0){
                edtUserName.setError("Required");
            }else if(edtPass.getText().length()==0){
                edtPass.setError("Required");
            }else if(edtEmail.getText().length()==0){
                edtEmail.setError("Required");
            }else if(edtConfirm.getText().toString().equals(edtPass.getText().toString())==false ){
                edtConfirm.setError("Not match");
            }else {
                Register();}
        });
        btnLoginNow.setOnClickListener(view -> {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        });
    }

    private void Register() {

        String username=edtUserName.getText().toString();
        String email=edtEmail.getText().toString().trim();
        String pass=edtPass.getText().toString();
        String confirm=edtConfirm.getText().toString().trim();
        User user=new User(username,email,pass);
        firebaseAuth.createUserWithEmailAndPassword(email,confirm).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(task1 -> {
                        if(task1.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register succesfully!", Toast.LENGTH_SHORT).show();
                            edtUserName.setText("");
                            edtConfirm.setText("");
                            edtPass.setText("");
                            edtEmail.setText("");
                            edtUserName.requestFocus();
                            Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Register failed!", Toast.LENGTH_SHORT).show();
                        }
                    } );

                }else{
                    Toast.makeText(RegisterActivity.this, "Unsuccess!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
