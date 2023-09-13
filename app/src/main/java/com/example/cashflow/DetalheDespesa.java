package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cashflow.datasource.DataSourceFirebase;
import com.example.cashflow.models.Despesa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.Objects;

public class DetalheDespesa extends AppCompatActivity {
    String usuarioID;
    private ImageView bt_voltar;

    private TextView valorDespesa;
    private TextView descricaoDespesa;
    private TextView categoriaDespesa;
    private TextView dataDespesa;


    private TextView latitudeDespesa;
    private TextView longitudeDespesa;


    private ListenerRegistration despesaListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_despesa);
        getSupportActionBar().hide();

        IniciarComponentes();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String despesaID = getIntent().getStringExtra("despesaID");
        Log.d("DetalheDespesa1", "ID da Despesa: " + despesaID);
        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        com.example.cashflow.datasource.DataSourceFirebase.getDespesas(usuarioID, new DataSourceFirebase.OnDespesasLoadedListener() {
            @Override
            public void onDespesasLoaded(ArrayList<Despesa> despesas) {
                // Procure a despesa pelo ID desejado
                Despesa despesaDetalhe = null;
                for (Despesa despesa : despesas) {
                    if (despesa.getIdDoFirestore().equals(despesaID)) {
                        despesaDetalhe = despesa;
                        break;
                    }
                }

                if (despesaDetalhe != null) {
                    // Aqui você tem a despesa com detalhes
                    // Atualize as TextViews com os detalhes da despesa
                    valorDespesa.setText(despesaDetalhe.getValor());
                    descricaoDespesa.setText(despesaDetalhe.getDescricao());
                    categoriaDespesa.setText(despesaDetalhe.getCategoria());
                    dataDespesa.setText(despesaDetalhe.getData());
                    latitudeDespesa.setText(String.valueOf(despesaDetalhe.getLatitude()));
                    longitudeDespesa.setText(String.valueOf(despesaDetalhe.getLongitude()));


                    // Outras atualizações conforme necessário
                } else {
                    // A despesa com o ID especificado não foi encontrada
                    // Lide com isso de acordo com sua lógica de erro
                }
            }

        });



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

        valorDespesa = findViewById(R.id.edit_valor_detalhe);
        descricaoDespesa = findViewById(R.id.edit_descricao_detalhe);
        categoriaDespesa = findViewById(R.id.spinnerCategoria);
        dataDespesa = findViewById(R.id.edit_data);
        latitudeDespesa = findViewById(R.id.text_local_latitude_detalhe);
        longitudeDespesa = findViewById(R.id.text_local_longitude_detalhe);

    }

    protected void onDestroy() {
        super.onDestroy();
        // Certifique-se de cancelar o ouvinte do Firestore quando a atividade for destruída
        if (despesaListener != null) {
            despesaListener.remove();
        }
    }
}
