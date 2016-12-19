package ilist.gabrielrunescape.com.br.adapter;

import java.util.List;
import android.view.View;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import ilist.gabrielrunescape.com.br.R;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import ilist.gabrielrunescape.com.br.object.Item;

/**
 * <h1>Classe ItemAdapter</h1>.
 * A classe extende um ArrayAdapter no qual é utilizado para inserir informações de um objeto a ele
 * exibindo em uma ListView.
 *
 * @author Gabriel
 * @version 0.1
 * @since 2016-12-18
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private List<Item> listItens;
    /**
     * Metódo construtor da classe.
     *
     * @param lista ArrayList serializado com Item.
     */
    public ItemAdapter(List<Item> lista) {
        listItens = lista;
    }

    /**
     * Sobreescreve o método onCreateViewHolder().
     * Cria o layout de cada item do RecyclerVeiw.
     *
     * @param parent RecyclerView.
     * @param viewType tipo da view.
     *
     * @return Um adaptador de RecyclerView.
     */
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ItemHolder(itemView);
    }

    /**
     * Sobreescreve o método onBindViewHolder().
     * Define os valores de cada Textview dentro do list_item.xml.
     *
     * @param holder Adaptador de RecyclerView.
     * @param position Posição o qual está preenchendo.
     */
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Item item = listItens.get(position);
        holder.nome.setText(item.getNome());
        holder.status.setText(item.getStatus());
    }

    /**
     * @return Número de elementos no RecyclerView.
     */
    @Override
    public int getItemCount() {
        return listItens.size();
    }

    /**
     * Ao clicar no item, determina uma ação.
     *
     * @param v ItemAdapter clicado.
     */
    //@Override
    public void onClick(View v) {
        v.setFocusable(true);
    }

    /**
     * Ao pressionar o item, exibe um AlertDialog.
     *
     * @param view ItemAdapter pressionado.
     * @return Se está ativo ou não o AlertDialog.
     */
    //@Override
    public boolean onLongClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Escolha uma ação");

        String[] options = new String[] { "Apagar", "Editar", "Inserir", "Visualizar" };
        view.setPressed(true);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            /**
             * Ao clicar em um dos itens, encaminha para uma Intent especifica da ação.
             *
             * @param dialog AlertDialog.Builder utilizado.
             * @param which Posição do item clicado.
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

        return builder.show().isShowing();
    }
}
