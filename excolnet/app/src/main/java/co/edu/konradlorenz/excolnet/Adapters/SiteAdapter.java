package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Activities.SitesActivity;
import co.edu.konradlorenz.excolnet.Entities.Lugar;
import co.edu.konradlorenz.excolnet.R;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteHolder> {

    private ArrayList<Lugar> lugares;
    private CardView cardViewLugares;
    private Context context;
    private String ACTIVITY_NAME = "SitesAdapter";
    private View view;


    public SiteAdapter(Context context, ArrayList<Lugar> lugares) {
        this.lugares = lugares;
        this.context = context;
    }


    @NonNull
    @Override
    public SiteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_site, parent, false);
        findMaterialElements();
        SiteHolder sth = new SiteHolder(view);
        return sth;
    }

    @Override
    public void onBindViewHolder(@NonNull final SiteHolder holder, final int position) {
        holder.tituloSite.setText(lugares.get(position).getTitulo());
        holder.descripcionSite.setText(lugares.get(position).getDescripcion());
        Glide.with(context).load(lugares.get(position).getImagen_lugar()).into(holder.imagenSite);
        cardViewLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SitesActivity.class);
                intent.putExtra("id", lugares.get(position).getId());
                intent.putExtra("latitud", lugares.get(position).getLatitud());
                intent.putExtra("nameActivity", ACTIVITY_NAME);

                intent.putExtra("longitud", lugares.get(position).getLongitud());
                intent.putExtra("titulo", lugares.get(position).getTitulo());

                view.getContext().startActivity(intent);
            }
        });
    }

    private void findMaterialElements() {
        cardViewLugares = view.findViewById(R.id.site_card);

    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public ArrayList<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(ArrayList<Lugar> lugares) {
        this.lugares = lugares;
    }


    public static class SiteHolder extends RecyclerView.ViewHolder {
        ImageView imagenSite;
        TextView tituloSite;
        TextView descripcionSite;

        public SiteHolder(@NonNull View itemView) {
            super(itemView);
            imagenSite = itemView.findViewById(R.id.imagen_site);
            tituloSite = itemView.findViewById(R.id.titulo_site);
            descripcionSite = itemView.findViewById(R.id.descripcion_site);
        }
    }

}
