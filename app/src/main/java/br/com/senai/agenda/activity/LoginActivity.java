package br.com.senai.agenda.activity;

// Importações necessárias para a classe funcionar
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.senai.agenda.R;

// Classe que representa a tela de login
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaração dos campos de email, senha e texto de erro
    private EditText campoEmail;
    private EditText campoSenha;
    private TextView erroText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Define o layout da tela de login

        // Obtém a referência do botão de entrar e define um listener de clique
        Button btn = (Button) findViewById(R.id.btn_entrar);
        btn.setOnClickListener(this);

        // Obtém as referências dos campos de email, senha e texto de erro
        campoEmail = (EditText) findViewById(R.id.cred_email);
        campoSenha = (EditText) findViewById(R.id.cred_senha);
        erroText = (android.widget.TextView) findViewById(R.id.textErroLogin);

        // Inicializa as SharedPreferences que persiste o login
        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);

        // Verifica se já existe um usuário logado
        if (sharedPreferences.contains("email") && sharedPreferences.contains("senha")) {
            // Se existir, inicia diretamente a MainActivity
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    // Método chamado quando um clique é detectado
    @Override
    public void onClick(View v) {
        // Verifica se o clique foi no botão de entrar
        if (v.getId() == R.id.btn_entrar){
            // Obtém os valores digitados nos campos de email e senha
            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();

            // Verifica se o email e senha estão corretos
            if (email.equals("guimello120@gmail.com") && senha.equals("123")){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("senha", senha);
                editor.apply();


                // Se as credenciais estiverem corretas, inicia a MainActivity
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            } else if (email.isEmpty()) {
                // Exibe mensagem de erro se o email estiver vazio
                erroText.setText("Email não pode estar vazio!!");
            } else if (senha.isEmpty()){
                // Exibe mensagem de erro se a senha estiver vazia
                erroText.setText("A senha não pode estar vazia!!");
            } else{
                // Exibe mensagem de erro se as credenciais estiverem incorretas
                erroText.setText("Email ou Senha incorretas!!!");
            }
        }
    }
}