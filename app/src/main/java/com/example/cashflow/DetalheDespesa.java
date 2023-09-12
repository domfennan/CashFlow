package com.example.cashflow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cashflow.models.Despesa;

public class DetalheDespesa extends AppCompatActivity {

    private ImageView bt_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_despesa);
        getSupportActionBar().hide();

        IniciarComponentes();


        String despesaID = getIntent().getStringExtra("despesaID");
        Log.d("DetalheDespesa1", "ID da Despesa: " + despesaID);


        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalheDespesa.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);

    }
}
