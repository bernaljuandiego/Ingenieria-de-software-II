package co.edu.konradlorenz.excolnet.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.Activities.DetailPublicationActivity;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.R;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private ArrayList<Publicacion> items;
    private Context context;
    private LinearLayout cardViewPublication;
    private View view;
    private FirebaseUser user;

    public PublicationAdapter(Context context, ArrayList<Publicacion> items, FirebaseUser user) {
        this.items = items;
        this.context = context;
        this.user = user;
    }

    public ArrayList<Publicacion> getItems() {
        return items;
    }

    public void setItems(ArrayList<Publicacion> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PublicationHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_publication, parent, false);
        cardViewPublication = view.findViewById(R.id.publication_card);
        PublicationHolder pvh = new PublicationHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationHolder holder, final int position) {
        holder.nombreUsuario.setText(items.get(position).getUsuario().getDisplayName());
        holder.fechaPublicacion.setText(items.get(position).getFechaPublicacion());
        holder.descripcionPublicacion.setText(items.get(position).getTexto());
        Glide.with(context).load(items.get(position).getUsuario().getPhotoUrl())
                .placeholder(R.drawable.ic_profile) //this would be your default image (like default profile or logo etc). it would be loaded at initial time and it will replace with your loaded image once glide successfully load image using url.
                .error(R.drawable.com_facebook_profile_picture_blank_square)//in case of any glide exception or not able to download then this image will be appear . if you won't mention this error() then nothing to worry placeHolder image would be remain as it is.
                .fitCenter()//this method help to fit image into center of your ImageView
                .apply(RequestOptions.circleCropTransform()).into(holder.fotoUsuario);
        Glide.with(context).load(items.get(position).getImagen()).into(holder.imagenPublicacion);
        Glide.with(context).load(user.getPhotoUrl())
                .placeholder(R.drawable.ic_profile) //this would be your default image (like default profile or logo etc). it would be loaded at initial time and it will replace with your loaded image once glide successfully load image using url.
                .error(R.drawable.com_facebook_profile_picture_blank_square)//in case of any glide exception or not able to download then this image will be appear . if you won't mention this error() then nothing to worry placeHolder image would be remain as it is.
                .fitCenter()//this method help to fit image into center of your ImageView
                .apply(RequestOptions.circleCropTransform()).into(holder.fotoUsuarioActual);

        //Maneja los clics de cada publicación.
        cardViewPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(view.getContext(), DetailPublicationActivity.class);
                newIntent.putExtra("user", items.get(position).getUsuario());
                newIntent.putExtra("publication_date", items.get(position).getFechaPublicacion());
                newIntent.putExtra("publication_description", items.get(position).getTexto());
                newIntent.putExtra("publication_image", items.get(position).getImagen());
                view.getContext().startActivity(newIntent);
            }
        });
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
        ImageView fotoUsuarioActual;
        PublicationHolder(View itemView) {
            super(itemView);
            fotoUsuarioActual = itemView.findViewById(R.id.user_imagen);
            fotoUsuario = itemView.findViewById(R.id.foto_usuario_publicacion);
            nombreUsuario = itemView.findViewById(R.id.usuario_publicacion);
            fechaPublicacion = itemView.findViewById(R.id.fecha_publicacion);
            descripcionPublicacion = itemView.findViewById(R.id.descripcion_publicacion);
            imagenPublicacion = itemView.findViewById(R.id.imagen_publicacion);
        }
    }

}
