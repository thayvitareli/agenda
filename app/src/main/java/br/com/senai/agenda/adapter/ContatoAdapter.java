package br.com.senai.agenda.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.senai.agenda.R;
import br.com.senai.agenda.activity.DetalheActivity;
import br.com.senai.agenda.model.Contato;

public class ContatoAdapter
        extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>  {

    private List<Contato> contatos;
    private Context context;



    public ContatoAdapter(List<Contato> contatos,
                          Context context) {
        this.contatos = contatos;
        this.context = context;
    }

    // Cria um item do view holder
    // Especificamos o layout que sera usado
    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(this.context)
                .inflate(R.layout.item_contato, parent, false);
        return new ContatoViewHolder(view);
    }

    // Faz a troca do conteúdo
    // Dentro do arquivo de layout
    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = contatos.get(position);
        holder.tvNome.setText(contato.getNome());


        // ADICIONANDO O EVENTO DE CLICK NO CARD
        holder.card.setOnClickListener(v -> {

            // CRIA UMA INTENT COM A ACTIVITY "DETALHEACTIVITY"
            Intent telaCadastro = new Intent(context, DetalheActivity.class);

            // PASSA OS VALORES DO OBJETO "CONTATO" PARA A ACTIVITY "TELA CADASTRO"
            telaCadastro.putExtra("nome", contato.getNome());
            telaCadastro.putExtra("fone", contato.getFone());
            telaCadastro.putExtra("email", contato.getEmail());
          //  telaCadastro.putExtra("id", contato.getId());

            //INICIA A ACTIVITY TELA CADASTRO
            holder.itemView.getContext().startActivity(telaCadastro);
        });
    }


    // Conta a qtde de itens da lista
    @Override
    public int getItemCount() {
        return this.contatos.size();
    }

    public class ContatoViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNome;

        CardView card;

        // Método para criar novos View Holder
        // Chamado pelo Layout Manager
        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNome = itemView.findViewById(R.id.tvNome);

            card = itemView.findViewById(R.id.card_list);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            }


        @Override
        public void onClick(View v) {


        }
    }
}
