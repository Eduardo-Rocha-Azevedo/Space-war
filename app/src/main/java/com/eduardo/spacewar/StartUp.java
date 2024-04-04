package com.eduardo.spacewar;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
    }

    public void startGame(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
