package ilist.gabrielrunescape.com.br.adapter;

import android.view.View;
import android.widget.TextView;
import ilist.gabrielrunescape.com.br.R;
import android.support.v7.widget.RecyclerView;

/**
 * <h1>Cloase extensora ItemHolder</h1>.
 *
 * Carrega os itens básicos para criar os ItemView da RecycleView.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-12-18
 */
public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView nome, status;

    /**
     * Método construtor da classe.
     * @param v Activity da RecycleView.
     */
    public ItemHolder(View v) {
        super(v);
        nome = (TextView) v.findViewById(R.id.txt_nome);
        status = (TextView) v.findViewById(R.id.txt_status);
    }
}
