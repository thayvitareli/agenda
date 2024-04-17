package br.com.senai.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.senai.agenda.R;
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
        holder.tvFone.setText(contato.getFone());
        holder.tvEmail.setText(contato.getEmail());
    }

    // Conta a qtde de itens da lista
    @Override
    public int getItemCount() {
        return this.contatos.size();
    }

    public class ContatoViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvFone;
        TextView tvEmail;

        // Método para criar novos View Holder
        // Chamado pelo Layout Manager
        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvFone = itemView.findViewById(R.id.tvFone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
