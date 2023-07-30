package com.example.cashflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NovaDespesa extends AppCompatActivity {

    private ImageView bt_voltar;
    private TextInputEditText editTextData;
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
                String categoriaSelecionada = categoriasDisponiveis.get(position);
                // Faça o que quiser com a categoria selecionada, por exemplo, armazenar em uma variável na sua atividade
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

        editTextData = findViewById(R.id.editTextData);
        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

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
        editTextData.setText(selectedDate);
    }

    private void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);
    }
}
