package br.com.senai.agenda.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.agenda.model.Contato;


public class ContatoDao {
    private SQLiteDatabase database;
    private SQLiteHelper helper;

    public ContatoDao(Context context) {
        this.helper = new SQLiteHelper(context);
    }

    public List<Contato> buscarTodosContatos() {
        this.database = helper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        String[] colunas = new String[] {
                SQLiteHelper.COL_ID,
                SQLiteHelper.COL_NAME,
                SQLiteHelper.COL_FONE,
                SQLiteHelper.COL_EMAIL
        };

        Cursor cursor = database.query(
                SQLiteHelper.DATABASE_TABLE,
                colunas, null, null, null, null,
                SQLiteHelper.COL_NAME
        );

        while (cursor.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(cursor.getLong(0));
            contato.setNome(cursor.getString(1));
            contato.setEmail(cursor.getString(3));
            contato.setFone(cursor.getString(2));
            contatos.add(contato);
        }
        cursor.close();
        database.close();

        return contatos;
    }

    public void salvarContato(Contato contato) {
        this.database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COL_NAME, contato.getNome());
        values.put(SQLiteHelper.COL_EMAIL, contato.getEmail());
        values.put(SQLiteHelper.COL_FONE, contato.getFone());

        if (contato.getId() == 0) {
            this.database.insert(
                    SQLiteHelper.DATABASE_TABLE,
                    null,
                    values
            );
        } else {
            this.database.update(
                    SQLiteHelper.DATABASE_TABLE,
                    values,
                    SQLiteHelper.COL_ID + "=" + contato.getId(),
                    null
            );
        }
    }

    public void apagarContato(Contato contato) {
        this.database = helper.getWritableDatabase();
        database.delete(
                SQLiteHelper.DATABASE_TABLE,
                SQLiteHelper.COL_ID + "=" + contato.getId(),
                null
        );
    }
}
