package com.mnewspaper.callingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Registration_Activity extends AppCompatActivity {

    TextView tologin;
    EditText txtname,txtemail,txtphone,txtpass;
    private Button regis;
    public static FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checStatusBar();
        setContentView(R.layout.activity_registration_);
        initregi();
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });




    }

    private void createAccount() {
        String name=txtname.getText().toString();
        String email=txtemail.getText().toString();
        String phone=txtphone.getText().toString();
        String password=txtpass.getText().toString();
        if (TextUtils.isEmpty(email)){
            txtemail.setError("required *");
        }else if (TextUtils.isEmpty(password)){
            txtpass.setError("required *");
        }
        else if (TextUtils.isEmpty(name)){
            txtname.setError("required *");
        }
        else if (TextUtils.isEmpty(phone)){
            txtphone.setError("requird *");
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        HashMap<Object,String> hashMap=new HashMap<>();
                        hashMap.put("name",name);
                        hashMap.put("email",email);
                        hashMap.put("phone",phone);
                        hashMap.put("password",password);
                        db.collection("users").document()
                                .set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Account create Successful!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration_Activity.this,LoginActivity.class));
                                Animatoo.animateFade(Registration_Activity.this);
                                finish();
                            }
                        });


                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    }

    private void initregi() {
        txtname=(EditText)findViewById(R.id.regisname);
        txtemail=(EditText)findViewById(R.id.regiemail);
        txtpass=(EditText)findViewById(R.id.regipassword);
        txtphone=(EditText)findViewById(R.id.regismobile);
        regis=(Button)findViewById(R.id.regibutton);
    }

    private void checStatusBar() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.primaryTextColor));
                // window.setStatusBarColor(getResources().getColor(R.color.regisbk));
                getSupportActionBar().hide();
            }


        }


}