package com.example.cashflow;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cashflow.MyNotification;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cashflow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_PERMISSION_CODE = 1;

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivityForResult(intent, NOTIFICATION_PERMISSION_CODE);
        }
    }

    public static final String CHANNEL_ID = "my_channel_id";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView text_tela_conta;
    private ImageView imageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        IniciarComponentes();

        createNotificationChannel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                // Permissão não concedida, exibir o AlertDialog para solicitar a permissão
                showPermissionAlertDialog();
            } else {
                // Permissão concedida, pode exibir a notificação
                MyNotification.showNotification(this);
            }
        }

        binding.appBarMain.btAdicionaDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, NovaDespesa.class);
                // Adicionar parâmetros extras, se necessário
                //intent.putExtra("chave", valor);
                startActivity(intent);// Iniciar a nova atividade
            }
        });



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_registros, R.id.nav_relatoiro)//, R.id.nav_mes,R.id.nav_ano
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        text_tela_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,TelaConta.class);
                startActivity(intent);
            }
        });
        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,TelaConta.class);
                startActivity(intent);
            }
        });
    }

    private void showPermissionAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissão de Notificação");
        builder.setMessage("Este aplicativo precisa de permissão para exibir notificações. Deseja conceder a permissão?");
        builder.setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestNotificationPermission();//ou MyNotification.showNotification(MainActivity.this);
            }
        });
        builder.setNegativeButton("Negar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lidar com o caso em que o usuário nega a permissão (opcional)
            }
        });
        builder.setCancelable(false); // Impedir que o usuário cancele o AlertDialog
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void IniciarComponentes(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        text_tela_conta = navigationView.getHeaderView(0).findViewById(R.id.textNomeUsuarioLateral);

        imageUser = navigationView.getHeaderView(0).findViewById(R.id.imageUser);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Registre o canal no NotificationManager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            // Verificar se a permissão de notificação foi concedida após a tela de configurações ser exibida
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                    // Permissão concedida, pode exibir a notificação
                    MyNotification.showNotification(this);
                } else {
                    // Permissão negada, exiba uma mensagem ou tome alguma outra ação
                    // se desejar
                }
            }
        }
    }

}