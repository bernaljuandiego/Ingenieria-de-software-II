package co.edu.konradlorenz.excolnet.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.Activities.PrincipalActivity;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.R;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private ArrayList<Publicacion> items;
    private Context context;

    public PublicationAdapter(Context context, ArrayList<Publicacion> items) {
        this.items = items;
        this.context = context;
    }

    public ArrayList<Publicacion> getItems() {
        return items;
    }

    public void setItems(ArrayList<Publicacion> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PublicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_publication, parent, false);
        PublicationHolder pvh = new PublicationHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationHolder holder, int position) {
        holder.nombreUsuario.setText(items.get(position).getUsuario().getDisplayName());
        holder.fechaPublicacion.setText(items.get(position).getFechaPublicacion());
        holder.descripcionPublicacion.setText(items.get(position).getTexto());
        Glide.with(context).load(items.get(position).getUsuario().getPhotoUrl()).into(holder.fotoUsuario);
        Glide.with(context).load(items.get(position).getImagen()).into(holder.imagenPublicacion);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class PublicationHolder extends RecyclerView.ViewHolder {
        ImageView fotoUsuario;
        TextView nombreUsuario;
        TextView fechaPublicacion;
        ImageView imagenPublicacion;
        TextView descripcionPublicacion;

        PublicationHolder(View itemView) {
            super(itemView);
            fotoUsuario = itemView.findViewById(R.id.foto_usuario_publicacion);
            nombreUsuario = itemView.findViewById(R.id.usuario_publicacion);
            fechaPublicacion = itemView.findViewById(R.id.fecha_publicacion);
            descripcionPublicacion = itemView.findViewById(R.id.descripcion_publicacion);
            imagenPublicacion = itemView.findViewById(R.id.imagen_publicacion);
        }
    }

}
