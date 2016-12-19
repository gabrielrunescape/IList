package ilist.gabrielrunescape.com.br.adapter;

import android.view.View;
import android.content.Context;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v7.widget.RecyclerView;
import ilist.gabrielrunescape.com.br.interfaces.ClickListener;

/**
 * <h1>Classe RecycleTouchListener</h1>.
 *
 * A classe implementa uma interface de RecyclerView ao tocar em um item.
 *
 * @author Ravi Tamada
 * @version 0.1
 * @since 2016-12+18
 */
public class RecycleTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gesture;
    private ClickListener clickListener;

    /**
     * Método construtor da classe RecycleTouchListener.
     *
     * @param c Contexto da aplicação.
     * @param r RecyclerView da activity em execução.
     * @param l Interface ClickListener.
     */
    public RecycleTouchListener(Context c, final RecyclerView r, final ClickListener l) {
        clickListener = l;

        gesture = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener() {
            @Override
            /**
             * Sobreescreve o método onSigleTapUp().
             *
             * @return Verdadeiro.
             */
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            /**
             * Sobreescreve o método onLongPress().
             * Passa a view (item do RecyclerView) que foi pressionado para o método onLongClick().
             */
            public void onLongPress(MotionEvent e) {
                View child = r.findChildViewUnder(e.getX(), e.getY());

                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, r.getChildPosition(child));
                }
            }
        });
    }

    /**
     * Sobreescreve o método onInterceptTouchEvent().
     * Intercepta qual view (item do RecyclerView) que foi pressionado e manda para onClick().
     *
     * @param rv RecyclerView da activity em execução.
     * @param e Eventoo qual foi utilizado.
     *
     * @return Falso.
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());

        if (child != null && clickListener != null && gesture.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }

        return false;
    }

    /**
     * Sobreescreve o método onTouchEvent().
     *
     * @param rv RecyclerView da activity em execução.
     * @param e Eventoo qual foi utilizado.
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
