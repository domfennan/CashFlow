package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.File;
import java.io.FileOutputStream;

public class TelaConta extends AppCompatActivity {

    private Button bt_deslogar;
    private ImageView bt_voltar;
    private ImageView imageUser;
    private TextView nomeUsuario,emailUsuario;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;


    private static final int CAMERA_REQUEST_CODE = 1001;
    private static final int CAMERA_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conta);
        getSupportActionBar().hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaConta.this,FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaConta.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar se a permissão da câmera já foi concedida
                if (ContextCompat.checkSelfPermission(TelaConta.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Se a permissão não foi concedida, solicite-a
                    ActivityCompat.requestPermissions(TelaConta.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                } else {
                    // Permissão já concedida, crie uma Intent para abrir a câmera
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Nenhum aplicativo de câmera encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bitmap savedImage = loadImageFromInternalStorage();

        if (savedImage != null) {
            // Carregue a imagem na ImageView
            imageUser.setImageBitmap(savedImage);
        }


        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUsuario.setText(email);
                }
            }
        });
    }

    private void IniciarComponentes(){
        bt_deslogar = findViewById(R.id.bt_deslogar);
        bt_voltar = findViewById(R.id.bt_voltar);
        imageUser = findViewById(R.id.imageUser);
        nomeUsuario = findViewById(R.id.textNomeUsuario);
        emailUsuario = findViewById(R.id.textEmailUsuario);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão da câmera foi concedida, crie a Intent para abrir a câmera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                } else {
                    Toast.makeText(getApplicationContext(), "Nenhum aplicativo de câmera encontrado", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permissão da câmera foi negada, você pode mostrar uma mensagem ao usuário se desejar
                Toast.makeText(getApplicationContext(), "Permissão da câmera negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void saveImageToInternalStorage(Bitmap bitmapImage){
        // Diretório onde a imagem será salva
        File directory = getDir("imageDir", Context.MODE_PRIVATE);

        // Nome do arquivo
        String fileName = "user_image.jpg";

        File file = new File(directory, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap loadImageFromInternalStorage() {
        try {
            File directory = getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(directory, "user_image.jpg");
            if (file.exists()) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // A imagem foi capturada com sucesso, agora você pode exibi-la na ImageView
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // Obtém a imagem capturada

            // Salve a imagem no armazenamento interno
            saveImageToInternalStorage(imageBitmap);
            // Define a imagem capturada na ImageView
            imageUser.setImageBitmap(imageBitmap);
        }
    }

}