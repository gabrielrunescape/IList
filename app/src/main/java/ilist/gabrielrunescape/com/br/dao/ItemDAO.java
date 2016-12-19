package ilist.gabrielrunescape.com.br.dao;

import java.util.List;
import android.util.Log;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import ilist.gabrielrunescape.com.br.object.Item;
import ilist.gabrielrunescape.com.br.database.VersionUtils;
import ilist.gabrielrunescape.com.br.database.CustomSQLiteOpenHelper;

/**
 * <h1>Classe ItemDAO</h1>
 *
 * Classe responsável por todos os eventos de inserir, alterar, apagar e exibir
 * todos os itens.
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-18
 */
public class ItemDAO {
    private Context context;
    private SQLiteDatabase database;
    private CustomSQLiteOpenHelper helper;
    private static String TAG = ItemDAO.class.getSimpleName(); // Logging

    /**
     * Método construtor da classe.
     *
     * @param c Contexto da aplicação.
     */
    public ItemDAO(Context c) {
        context = c;

        try {
            Log.i(TAG, "Lendo banco banco de dados ...");
            helper = new CustomSQLiteOpenHelper(c, "iList_dbase", VersionUtils.getVersionCode(c));
        } catch (SQLException ex) {
            Log.e(TAG, "Erro ao ler o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Abre o banco de dados.
     * @param writable Falso para somente leitura.
     */
    public void open(boolean writable) {
        try {
            Log.i(TAG, "Abrindo banco de dados ...");

            if (writable) {
                database = helper.getWritableDatabase();
            } else {
                database = helper.getReadableDatabase();
            }
        } catch (SQLException ex) {
            Log.e(TAG, "Erro ao abrir o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Fecha o banco de dados se estiver em aberto.
     */
    @Override
    protected void finalize() {
        try {
            super.finalize();

            if (database != null && database.isOpen()) {
                Log.i(TAG, "Finalizando banco de dados");
                database.close();
            }
        } catch (Throwable ex) {
            Log.e(TAG, "Erro ao finalizar o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Fecha o banco de dados.
     */
    public void closeDatabase() {
        database.close();
    }

    public Item insert(Item i) {
        ContentValues values = new ContentValues();

        try {
            values.put("Nome", i.getNome());
            values.put("Status", i.getStatus());

            Log.i(TAG, "Inserindo item ... ");
            long id = database.insert("`Item`", null, values);

            Cursor cursor = database.rawQuery("SELECT * FROM `Item` WHERE ID = " + id, null);
            cursor.moveToFirst();

            Item item = new Item();

            item.setID(cursor.getLong(0));
            item.setNome(cursor.getString(1));
            item.setStatus(cursor.getString(2));

            cursor.close();

            Log.i(TAG, "Item inserido com sucessso!");

            return item;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            return null;
        }
    }

    /**
     * Obtem todos os registros de transações dentro do banco de dados.
     *
     * @return uma lista do tipo Transaction.
     */
    public List<Item> getAll() {
        List<Item> item = new ArrayList<>();
        String query = "SELECT I.ID, I.NOME, S.Descricao, I.Status FROM Item I INNER JOIN Status S ON I.Status = S.ID";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            while (!cursor.isAfterLast()) {
                Item i = new Item();

                i.setID(cursor.getLong(0));
                i.setNome(cursor.getString(1));
                i.setStatus(cursor.getString(2));

                item.add(i);
                cursor.moveToNext();

                Log.i(TAG, "Obtendo itens ...");
            }

            cursor.close();
            return item;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, ex.getMessage());

            cursor.close();
            return null;
        }
    }
}
