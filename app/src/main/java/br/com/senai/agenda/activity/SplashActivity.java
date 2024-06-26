package br.com.senai.agenda.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.senai.agenda.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                carregaMain();
            }
        }, 9000);
    }

    private void carregaMain() {
        Intent telaPrincipal = new Intent(
                this, MainActivity.class);
        startActivity(telaPrincipal);
        finish();
    }
}