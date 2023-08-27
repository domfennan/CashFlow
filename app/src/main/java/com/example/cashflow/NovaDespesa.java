package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cashflow.repositorio.DespesasRepositorio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NovaDespesa extends AppCompatActivity {

    private ImageView bt_voltar;
    private EditText edit_data;

    private Button bt_salvar;

    private EditText edit_valor, edit_descricao;

    private String categoriaSelecionada;
    String[] mesagens = {"Preencha todos os campos", "Despesa salva com sucesso"};

    private List<String> categoriasDisponiveis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_despesa);
        getSupportActionBar().hide();
        IniciarComponentes();



        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);

        // Adicione as categorias disponíveis à lista
        categoriasDisponiveis.add("Alimentação");
        categoriasDisponiveis.add("Transporte");
        categoriasDisponiveis.add("Compras");
        categoriasDisponiveis.add("Carro");
        categoriasDisponiveis.add("Educação");
        categoriasDisponiveis.add("Família");
        categoriasDisponiveis.add("Lazer");
        categoriasDisponiveis.add("Saúde");
        categoriasDisponiveis.add("Vestuário");
        categoriasDisponiveis.add("outros");

        // Adicione mais categorias conforme necessário

        // Crie um ArrayAdapter usando a lista de categorias e um layout de spinner padrão
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasDisponiveis);

        // Defina o layout do dropdown do Spinner (opcional)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Vincule o ArrayAdapter ao Spinner
        spinnerCategoria.setAdapter(adapter);

        // Defina um listener para saber quando o usuário selecionou uma categoria no Spinner
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelecionada = categoriasDisponiveis.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Este método é chamado quando nenhum item é selecionado
            }
        });




        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovaDespesa.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edit_data = findViewById(R.id.edit_data);
        edit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valor = edit_valor.getText().toString();
                String descricao = edit_descricao.getText().toString();
                String data = edit_data.getText().toString();

                if (valor.isEmpty() || descricao.isEmpty() || data.isEmpty() ) {
                    Snackbar snackbar = Snackbar.make(view, mesagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    DespesasRepositorio.salvarDespesa(valor,descricao,categoriaSelecionada,data);
                    Snackbar snackbar = Snackbar.make(view, mesagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void CadastrarDespesa(View view){

        
    };

    public void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        updateDateEditText(calendar);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void updateDateEditText(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        edit_data.setText(selectedDate);
    }

    private void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);

        edit_valor = findViewById(R.id.edit_valor );
        edit_descricao = findViewById(R.id.edit_descricao );
        edit_data = findViewById(R.id.edit_data );

        bt_salvar = findViewById(R.id.bt_salvar);
    }
}
