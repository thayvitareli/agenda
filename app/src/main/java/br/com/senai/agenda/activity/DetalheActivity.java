package br.com.senai.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import br.com.senai.agenda.R;
import br.com.senai.agenda.data.ContatoDao;
import br.com.senai.agenda.model.Contato;

public class DetalheActivity extends AppCompatActivity implements View.OnClickListener {

    private ContatoDao contatoDao;
    private EditText etNome;
    private EditText etEmail;
    private EditText etFone;
    private Button btSalvar;
    private Button btVoltar;

    private TextView textErro;

    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        contatoDao = new ContatoDao(this);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etFone = findViewById(R.id.etFone);

        btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(this);

        btVoltar = findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(this);

        textErro = findViewById(R.id.textErro);

        Intent intent = getIntent();


     // INSTANCIA o objeto declarado
        this.contato = new Contato();


        /* VERIFICA SE FOI PASSANDO ALGUMA STRING EXTRA COM AS CHAVES: "NOME, FONE, EMAIL E ID"
         AO CHAMAR A ACTIVITY E SETA O OBJETO COM ESSES VALORES, JUNTAMENTE COM OS INPUTS DA TELA*/

        if(intent.hasExtra("id")){
            contato.setId(Long.parseLong(intent.getStringExtra("id")));
            contato.setNome(intent.getStringExtra("nome"));
            contato.setFone(intent.getStringExtra("fone"));
            contato.setEmail(intent.getStringExtra("email"));

            etNome.setText(contato.getNome());
            etFone.setText(contato.getFone());
            etEmail.setText(contato.getEmail());
        }
    }

    @Override
    public void onClick(View v) {
        // verifica qual botão foi acionado, chamando a função de acordo com a id do botão

        if (v.getId() == R.id.btVoltar || v.getId() == R.id.ibVoltar) {
            finish();
        } else if (v.getId() == R.id.btSalvar) {
            // salvar ou atualizar o contato
            salvarContato();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else if(v.getId() == R.id.btDeletar){

            //deleta o contato
            deletarContato(contato);

            // retorna para o main activity
            finish();
        }
    }

    public void salvarContato() {
        Contato contato = new Contato();
        contato.setNome(etNome.getText().toString());
        contato.setFone(etFone.getText().toString());
        contato.setEmail(etEmail.getText().toString());
        contato.setId(0);

        if(String.valueOf(this.contato.getId()) != null){
            contato.setId(this.contato.getId());
        }

        if(contato.getNome().isEmpty()){
            textErro.setText("O preenchimento do nome é obrigatório");

        }else if(contato.getFone().isEmpty()){
            textErro.setText("O preenchimento do telefone é obrigatório");

        }else{

            this.contatoDao.salvarContato(contato);
            Toast.makeText(this, "Contato salvo com sucesso",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void deletarContato(Contato contato) {

        contatoDao.apagarContato(contato);
        Toast.makeText(this, "Contato excluído com sucesso",
                Toast.LENGTH_SHORT).show();

    }
}