package com.mnewspaper.callingapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class LoginActivity extends AppCompatActivity {

    private TextView toregistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checStatusBar();
        setContentView(R.layout.layout_login);
        init();
    }

    private void init() {
        toregistration=(TextView)findViewById(R.id.transferregistration);
        toregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,Registration_Activity.class));
                Animatoo.animateFade(LoginActivity.this);
                finish();

            }
        });
    }

    private void checStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getSupportActionBar().hide();

        }

    }
}
