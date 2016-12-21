package ilist.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import ilist.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;
import ilist.gabrielrunescape.com.br.dao.ItemDAO;
import ilist.gabrielrunescape.com.br.object.Item;
import ilist.gabrielrunescape.com.br.dao.UnidadeDAO;
import ilist.gabrielrunescape.com.br.object.Unidade;

/**
 * <h1>Classe ItemActivity</h1>.
 *
 * É uma extensora da classe Activity(AppCompatActivity) que realiza funções de criar, inicializar
 * e exibir view em sua activity.
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-19
 */
public class ItemActivity extends AppCompatActivity {
    private UnidadeDAO dao;
    private Spinner spinner;
    private EditText et_Nome;
    private EditText et_Quantidade;
    private static String TAG = ItemActivity.class.getSimpleName();

    /**
     * Sobreescreve o método extendido onCreate() que cria todos as view necessárias..
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        dao = new UnidadeDAO(this);

        et_Nome = (EditText) findViewById(R.id.et_nome);
        spinner = (Spinner) findViewById(R.id.spinner_unidade);
        et_Quantidade = (EditText) findViewById(R.id.et_quantidade);

        dao.open(false);

        Log.i(TAG, "Método onCreate()");
    }

    /**
     * Sobreescreve o método extendido onResume() que carrega os demais elementos da activity.
     */
    @Override
    protected void onResume() {
        dao.open(false);
        super.onResume();

        try {
            ArrayAdapter<Unidade> adapter = dao.getArrayAdapter(this);
            spinner.setAdapter(adapter);
            Log.i(TAG, "Método onResume()");
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            ex.printStackTrace();
        }
    }

    /**
     * Sobreescreve o método extendido onCreateOptionsMenu() e importa um menu para Toolbar
     *
     * @param menu Toolbar da aplicação.
     * @return Verdadeiro.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.item_delete).setVisible(false);

        return true;
    }

    /**
     * Sobreeescreve o método extendido onOptionsItemSelected() e define a função de cada item.
     *
     * @param item Item no qual foi selecionado.
     * @return O item selecionado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_done:
                doneTransaction();
                break;
            default:
                Toast.makeText(this, "Função não programada!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método usado para implementar na inserção de uma transação fora
     * do método onOptionsItemSelected().
     */
    private void doneTransaction() {
        try {
            ItemDAO dao = new ItemDAO(this);

            String description = et_Nome.getText().toString();
            String quantity = et_Quantidade.getText().toString();

            if (description.isEmpty() || quantity.isEmpty()) {
                Toast.makeText(this, "Existem campos vazios!", Toast.LENGTH_LONG).show();
            } else {
                dao.open(true);
                Item item = new Item();

                item.setStatus("1");
                item.setNome(description);
                item.setQuantidade(Integer.parseInt(quantity));
                item.setUnidade(spinner.getSelectedItemPosition() + "");

                item = dao.insert(item);
                dao.closeDatabase();

                if (item != null) {
                    Toast.makeText(this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();

                    Log.i(TAG, "Item inserido com sucesso!");
                } else {
                    Toast.makeText(this, "Houve um erro ao inserir", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();

            Toast.makeText(this, "Foi encontrado um erro.", Toast.LENGTH_SHORT).show();
        }
    }
}
