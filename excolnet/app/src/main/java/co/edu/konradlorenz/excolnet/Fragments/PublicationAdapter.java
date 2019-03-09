package co.edu.konradlorenz.excolnet.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.R;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.UsuarioHolder> {

    private ArrayList<Asistencia> items;

    public UsuarioAdapter(ArrayList<Asistencia> items) {
        this.items = items;
    }

    public ArrayList<Asistencia> getItems() {
        return items;
    }

    public void setItems(ArrayList<Asistencia> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_usuario, parent, false);
        UsuarioHolder pvh = new UsuarioHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        holder.nombre.setText(items.get(position).getAsistente().getNombreAsistente() + " " + items.get(position).getAsistente().getApellidoAsistente());
        holder.codigo.setText(items.get(position).getAsistente().getCodigoAsistente() + " / " + Integer.toString(items.get(position).getAsistente().getCedulaAsistente()));
        holder.fecha.setText(items.get(position).getFecha());
        holder.uso.setText(items.get(position).getUso());
        //holder.imageView.setImageResource(items.get(position).getImagen());
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
            fotoUsuario = (ImageView) itemView.findViewById(R.id.foto_usuario_publicacion);


        }
    }

}
