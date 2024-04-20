package br.com.senai.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private Button btDeletar;

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

        // VERIFICAMOS FOI PASSANDO ALGUMA STRING EXTRA COM ESSAS CHAVES "nome, fone E email" AO CHAMAR A ACTIVITY
        // E INSTANCIAMOS UM OBJETO COM OS VALORES





        // COLOCAMOS OS VALORES DO OBJETO NO EditText
        etNome.setText(intent.getStringExtra("nome"));
        etFone.setText(intent.getStringExtra("fone"));
        etEmail.setText(intent.getStringExtra("email"));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btVoltar || v.getId() == R.id.ibVoltar) {
            finish();
        } else if (v.getId() == R.id.btSalvar) {
            // salvar ou atualizar o contato
            salvarContato();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else if(v.getId() == R.id.btDeletar){

            deletarContato();
        }
    }

    public void salvarContato() {
        Contato contato = new Contato();
        contato.setNome(etNome.getText().toString());
        contato.setFone(etFone.getText().toString());
        contato.setEmail(etEmail.getText().toString());
        contato.setId(0);


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

    public void deletarContato() {

        contatoDao.apagarContato(contato);

    }
}