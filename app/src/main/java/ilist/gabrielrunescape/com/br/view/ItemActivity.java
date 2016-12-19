package ilist.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;
import ilist.gabrielrunescape.com.br.R;
import ilist.gabrielrunescape.com.br.dao.ItemDAO;
import ilist.gabrielrunescape.com.br.object.Item;

import android.support.v7.app.AppCompatActivity;

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
    private EditText et_Nome;

    /**
     * Sobreescreve o método extendido onCreate() que cria todos as view necessárias..
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        et_Nome = (EditText) findViewById(R.id.et_Nome);
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

            dao.open(true);
            Item item = new Item(description, "1");

            item = dao.insert(item);
            dao.closeDatabase();

            if (item != null) {
                Toast.makeText(this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Houve um erro ao inserir", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Foi encontrado um erro.", Toast.LENGTH_SHORT).show();
        }
    }
}
