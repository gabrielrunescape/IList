package ilist.gabrielrunescape.com.br.interfaces;

import android.view.View;

/**
 * <h1>Interface ClickLIstener</h1>.
 *
 * Interface utilizada para definir quais serão os EventListeners que terão um item de uma
 * RecyclerView.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}