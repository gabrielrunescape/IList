package ilist.gabrielrunescape.com.br.view;

import java.util.List;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.DialogInterface;
import ilist.gabrielrunescape.com.br.R;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;

import ilist.gabrielrunescape.com.br.dao.ItemDAO;
import ilist.gabrielrunescape.com.br.object.Item;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import ilist.gabrielrunescape.com.br.adapter.ItemAdapter;
import ilist.gabrielrunescape.com.br.interfaces.ClickListener;
import ilist.gabrielrunescape.com.br.adapter.RecycleTouchListener;

/**
 * <h1>Classe HomeActivity</h1>.
 *
 * É uma extensora da classe Activity(AppCompatActivity) que realiza funções de criar, inicializar
 * e exibir view em sua activity.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-12-12
 */
public class HomeActivity extends AppCompatActivity {
    private ItemDAO dao;
    private List<Item> itens;
    private RecyclerView recyclerView;
    private static String TAG = HomeActivity.class.getSimpleName();

    /**
     * Sobreeescreve o método onCreate() que cria todos as view necessárias.
     * @param savedInstanceState Última Bundle salvada.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dao = new ItemDAO(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_itens);

        dao.open(true);
    }

    /**
     * Sobreescreve o método onResume() que inicializa algumas views.
     */
    @Override
    protected void onResume() {
        dao.open(true);
        super.onResume();

        try {
            itens = dao.getAll();

            ItemAdapter adapter = new ItemAdapter(itens);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

            Log.i(TAG, "Carregando RecyclerView e seu layout.");

            touchListenerInRecyclerView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Define os eventos que terá a RecyclerView.
     */
    private void touchListenerInRecyclerView() {
        recyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            /**
             * Ao clicar no item, realiza uma ação.
             *
             * @param view Item do RecyclerView clicado.
             * @param position Posição da view (item do RecyclerView).
             */
            public void onClick(View view, int position) {
                Log.i(TAG, String.format("O item nº %1$d foi clicado!", position));
            }

            @Override
            /**
             * Ao pressionar o item, exibe um AlertDialog.
             *
             * @param view Item do RecyclerView pressionado.
             * @param position Posição da view (item do RecyclerView).
             */
            public void onLongClick(final View view, int position) {
                Log.i(TAG, String.format("O item nº %1$d foi pressionado!", position));

                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Escolha uma ação");

                    String[] options = new String[] {"Apagar", "Editar", "Inserir", "Visualizar"};
                    view.setPressed(true);

                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        /**
                         * Ao clicar em um dos itens, encaminha para uma Intent especifica da ação.
                         *
                         * @param dialog AlertDialog.Builder utilizado.
                         * @param which  Posição do item clicado.
                         */
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                    Toast.makeText(view.getContext(), "Função não programada!", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });

                    builder.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }));
    }
}
