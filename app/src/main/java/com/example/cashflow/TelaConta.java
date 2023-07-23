package com.example.cashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TelaConta extends AppCompatActivity {

    private Button bt_deslogar;
    private ImageView bt_voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conta);
        getSupportActionBar().hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaConta.this, FormLogin.class);
                startActivity(intent);
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaConta.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void IniciarComponentes(){
        bt_deslogar = findViewById(R.id.bt_deslogar);
        bt_voltar = findViewById(R.id.bt_voltar);
    }
}