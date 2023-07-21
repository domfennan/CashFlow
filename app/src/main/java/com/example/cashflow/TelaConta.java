package com.example.cashflow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TelaConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conta);

        getSupportActionBar().hide();
    }
}