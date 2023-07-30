package com.example.cashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EditarDespesa extends AppCompatActivity {

    private ImageView bt_voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_despesa);
        getSupportActionBar().hide();

        IniciarComponentes();
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EditarDespesa.this,DetalheDespesa.class);
                startActivity(intent);
            }
        });
    }
    private void IniciarComponentes(){
        bt_voltar = findViewById(R.id.bt_voltar);
    }
}