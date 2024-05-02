package br.com.senai.agenda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.senai.agenda.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText campoEmail;
    private EditText campoSenha;
    private TextView erroText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btn = (Button) findViewById(R.id.btn_entrar);
        btn.setOnClickListener(this);
        campoEmail = (EditText) findViewById(R.id.cred_email);
        campoSenha = (EditText) findViewById(R.id.cred_senha);
        erroText = (android.widget.TextView) findViewById(R.id.textErroLogin);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_entrar){
            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();
            if (email.equals("guimello120@gmail.com") && senha.equals("123")){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            } else if (email.isEmpty()) {
                erroText.setText("Email não pode estar vazio!!");
            } else if (senha.isEmpty()){
                erroText.setText("A senha não pode estar vazia!!");
            }
            else{
                erroText.setText("Email ou Senha incorretas!!!");
            }
        }
    }
}

