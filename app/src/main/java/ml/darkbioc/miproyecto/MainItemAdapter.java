package ml.darkbioc.miproyecto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.TarjViewHolder> implements View.OnClickListener {

    private static ArrayList<MainItem> items;
    private View.OnClickListener listener;

    public MainItemAdapter(ArrayList<MainItem> items) {
        this.items = items;
    }

    public class TarjViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private ImageView iv;

        public TarjViewHolder(final View itemView) {
            super(itemView);

            itemView.setClipToOutline(true);
            /*itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    // Obtenemos el centro de la vista para realizar la animación circular.
                    int cx = itemView.getWidth();
                    int cy = itemView.getHeight();

                    // Calculamos el máximo radio posible
                    float radius = calculateMaxRadius(v);
                    Animator anim = ViewAnimationUtils.createCircularReveal(itemView, cx, cy, 0, radius);

                    // Cuando la animación ha finalizado la imagen permanecerá invisible
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            itemView.setVisibility(View.VISIBLE);
                        }
                    });

                    // Comenzamos la animación circular reveal.
                    anim.setDuration(500);
                    anim.start();
                }
            });*/

            texto = (TextView) itemView.findViewById(R.id.txt1);
            iv = itemView.findViewById(R.id.iv1);
        }

        public void bindTitular(MainItem t) {
            texto.setText(t.getTexto());
            iv.setImageResource(t.getImage());
        }
    }

    /*static float calculateMaxRadius(View view) {
        float widthSquared = view.getWidth() * view.getWidth();
        float heightSquared = view.getHeight() * view.getHeight();
        float radius = (float) Math.sqrt(widthSquared + heightSquared / 2);
        return radius;
    }*/

    @Override
    public TarjViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_items, viewGroup, false);
        itemView.setOnClickListener(this);
        TarjViewHolder tvh = new TarjViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TarjViewHolder viewHolder, int pos) {
        MainItem item = items.get(pos);
        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}