package com.example.a19518901_hocongviet_t3_10_12;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAcitivity extends AppCompatActivity {
    Button btnSave;
    EditText edtNAme,edtAuthor,edtPrice,edtDesc;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);
        btnSave=findViewById(R.id.btnSave_add_e);
        edtNAme=findViewById(R.id.txtBookName_add_e);
        edtAuthor=findViewById(R.id.txtAuthor_add_e);
        edtPrice=findViewById(R.id.txtPrice_add_e);
        edtDesc=findViewById(R.id.txtDescription_add_e);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("books");
        btnSave.setOnClickListener(view -> {
            String name=edtNAme.getText().toString();
            String auhtor=edtAuthor.getText().toString();
            String price=edtPrice.getText().toString();
            String desc=edtDesc.getText().toString();
            Book book=new Book(name,auhtor,price,desc);
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        edtAuthor.setText("");
                        edtDesc.setText("");
                        edtPrice.setText("");
                        edtNAme.setText("");
                        edtNAme.requestFocus();
                        Toast.makeText(AddAcitivity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddAcitivity.this, "aDD Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }Toast.makeText(AddAcitivity.this, "aDD Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}
