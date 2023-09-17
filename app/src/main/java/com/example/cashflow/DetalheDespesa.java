package com.example.cashflow;

import static com.example.cashflow.datasource.DataSourceFirebase.atualizarDetalhesDespesa;
import static com.example.cashflow.datasource.DataSourceFirebase.excluirDespesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView bt_delete;

    private TextView valorDespesa;
    private TextView descricaoDespesa;
    private TextView categoriaDespesa;
    private TextView dataDespesa;


    private TextView latitudeDespesa;
    private TextView longitudeDespesa;


    private ListenerRegistration despesaListener;

    private ImageView bt_editar;


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

        com.example.cashflow.datasource.DataSourceFirebase.registrarOuvinteExclusaoDespesa(usuarioID, despesaID, new DataSourceFirebase.OnDespesaExcluidaListener() {
            @Override
            public void onDespesaExcluida() {
                // Despesa excluída com sucesso, você pode tomar ação aqui, como retornar à lista de despesas
                Intent intent = new Intent(DetalheDespesa.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalheDespesa.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Exibir um AlertDialog de confirmação antes de excluir
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalheDespesa.this);
                builder.setTitle("Confirmar Exclusão");
                builder.setMessage("Tem certeza de que deseja excluir esta despesa?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Se o usuário clicar em "Sim", exclua a despesa
                        excluirDespesa(usuarioID, despesaID);
                        Toast.makeText(DetalheDespesa.this, "Despesa excluída com sucesso", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Se o usuário clicar em "Cancelar", feche o AlertDialog
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalheDespesa.this);
                builder.setTitle("Confirmar Edição");
                builder.setMessage("Tem certeza de que deseja salvar as alterações?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Obtenha os novos valores dos campos de texto
                        String novoDespesaText = valorDespesa.getText().toString();
                        String novaDescricaoText = descricaoDespesa.getText().toString();

                        // Chame o método para atualizar a despesa
                        atualizarDetalhesDespesa(usuarioID, despesaID, novoDespesaText, novaDescricaoText);

                        // Exiba uma mensagem de sucesso
                        Toast.makeText(DetalheDespesa.this, "Edição da despesa foi salva com sucesso", Toast.LENGTH_SHORT).show();

                        // Aguarde alguns segundos antes de voltar para a MainActivity
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(DetalheDespesa.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000); // 2000 milissegundos (2 segundos) de atraso
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // O usuário cancelou a edição, não faça nada
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });




    }


    private void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);
        bt_delete = findViewById(R.id.bt_delete);

        valorDespesa = findViewById(R.id.edit_valor_detalhe);
        descricaoDespesa = findViewById(R.id.edit_descricao_detalhe);
        categoriaDespesa = findViewById(R.id.spinnerCategoria);
        dataDespesa = findViewById(R.id.edit_data);

        latitudeDespesa = findViewById(R.id.text_local_latitude_detalhe);
        longitudeDespesa = findViewById(R.id.text_local_longitude_detalhe);

        bt_editar = findViewById(R.id.bt_editar);
    }

    protected void onDestroy() {
        super.onDestroy();
        // Certifique-se de cancelar o ouvinte do Firestore quando a atividade for destruída
        if (despesaListener != null) {
            despesaListener.remove();
        }
    }
}
