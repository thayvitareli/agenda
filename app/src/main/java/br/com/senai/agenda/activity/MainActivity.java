package br.com.senai.agenda.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.agenda.R;
import br.com.senai.agenda.adapter.ContatoAdapter;
import br.com.senai.agenda.data.ContatoDao;
import br.com.senai.agenda.model.Contato;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Contato> contatos = new ArrayList<>();
    private ContatoAdapter contatoAdapter;
    private ContatoDao contatoDao;

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    contatos.clear();
                    contatos.addAll(contatoDao.buscarTodosContatos());
                    contatoAdapter.notifyDataSetChanged();

//                    if (o.getResultCode() == RESULT_OK) {
//                        contatos.clear();
//                        contatos.addAll(contatoDao.buscarTodosContatos());
//                        contatoAdapter.notifyDataSetChanged();
//                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvListaContatos);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        contatoDao = new ContatoDao(this);
        buscarContatos();
        contatoAdapter = new ContatoAdapter(contatos, this);
        recyclerView.setAdapter(contatoAdapter);

        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navegar_cadastro) {
            // navegar para tela de cadastro
            Intent telaCadastro = new Intent(this, DetalheActivity.class);
            startActivityIntent.launch(telaCadastro);
        }
        return super.onOptionsItemSelected(item);
    }

    private void buscarContatos() {
//
        this.contatos = contatoDao.buscarTodosContatos();
    }




    @Override
    public void onClick(View v) {

    }
}