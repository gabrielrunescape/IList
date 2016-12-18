package ilist.gabrielrunescape.com.br.view;

import android.os.Bundle;
import ilist.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;

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
    /**
     * Sobreeescreve a classe onCreate() que cria todos as view necessárias.
     * @param savedInstanceState Última Bundle salvada.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
