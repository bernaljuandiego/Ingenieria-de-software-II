package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.konradlorenz.excolnet.Activities.DetailPublicationActivity;
import co.edu.konradlorenz.excolnet.Activities.ProfileActivity;
import co.edu.konradlorenz.excolnet.Entities.Comentario;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private ArrayList<Publicacion> items;
    private LinearLayout cardViewPublication;
    private Context context;
    private LinearLayout userPublicationClick;
    private String ACTIVITY_NAME = "PublicationsAdapter";
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
        findMaterialElements();
        PublicationHolder pvh = new PublicationHolder(view);
        return pvh;

    }

    private void findMaterialElements() {
        cardViewPublication = view.findViewById(R.id.publication_card);
        userPublicationClick = view.findViewById(R.id.user_publication_click);
    }

    @Override
    public void onBindViewHolder(@NonNull final PublicationHolder holder, final int position) {
        holder.nombreUsuario.setText(items.get(position).getUsuario().getDisplayName());
        holder.fechaPublicacion.setText(items.get(position).getFechaPublicacion());
        holder.descripcionPublicacion.setText(items.get(position).getTexto());
        for (Usuario usuario: items.get(position).getUsuariosQueGustan()){
            if(usuario.getUid()==user.getUid()){
                Log.e("juan", "local "+usuario.getUid()+" bd "+user.getUid());
                holder.botonLike.setImageResource(R.drawable.ic_like_selected);
            }
        }

        try {
            holder.cantidadLikes.setText(items.get(position).getUsuariosQueGustan().size() + " Likes");
            holder.cantidadComentarios.setText(items.get(position).getComentarios().size() + " Comments");
        } catch (NullPointerException e) {
        }

        Glide.with(context).load(items.get(position).getUsuario().getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).fitCenter().apply(RequestOptions.circleCropTransform()).into(holder.fotoUsuario);
        Glide.with(context).load(items.get(position).getImagen()).into(holder.imagenPublicacion);
        Glide.with(context).load(user.getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).fitCenter().apply(RequestOptions.circleCropTransform()).into(holder.fotoUsuarioActual);

        holder.botonComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.comentario.getText().toString() != "") {
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(new Date());
                    Usuario newUser = new Usuario(user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString(), user.getUid());
                    items.get(position).getComentarios().add(new Comentario(newUser, holder.comentario.getText().toString(), date));
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
                    mDatabase.child("Publicaciones").child(items.get(position).getId()).setValue(items.get(position));
                    holder.comentario.setText("");
                }
            }
        });

        holder.botonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean likeado = false;
                Usuario userDislike = null;
                for (Usuario usuario: items.get(position).getUsuariosQueGustan()){
                    if(usuario.getUid()==user.getUid()){
                        likeado = true;
                        userDislike = usuario;
                    }
                }
                if (likeado){
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
                    items.get(position).getUsuariosQueGustan().remove(userDislike);
                    mDatabase.child("Publicaciones").child(items.get(position).getId()).setValue(items.get(position));

                } else {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
                    items.get(position).getUsuariosQueGustan().add(new Usuario(user.getDisplayName(),user.getEmail(),user.getPhotoUrl().toString(),user.getUid()));
                    mDatabase.child("Publicaciones").child(items.get(position).getId()).setValue(items.get(position));
            }
            }
        });

        cardViewPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(view.getContext(), DetailPublicationActivity.class);
                newIntent.putExtra("id", items.get(position).getId());
                newIntent.putExtra("ACTIVITY_CALLED_NAME", ACTIVITY_NAME);
                newIntent.putExtra("USER_ID", items.get(position).getUsuario().getUid());
                newIntent.putExtra("USER", items.get(position).getUsuario());
                view.getContext().startActivity(newIntent);
            }
        });

        userPublicationClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(view.getContext(), "Profile Clicked", Toast.LENGTH_SHORT).show();
                Intent newIntent2 = new Intent(view.getContext(), ProfileActivity.class);
                newIntent2.putExtra("ACTIVITY_CALLED_NAME", ACTIVITY_NAME);
                newIntent2.putExtra("USER_ID", items.get(position).getUsuario().getUid());
                newIntent2.putExtra("USER", items.get(position).getUsuario());
                view.getContext().startActivity(newIntent2);
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
        TextView cantidadComentarios;
        TextView cantidadLikes;
        EditText comentario;
        Button botonComentar;
        ImageView botonLike;

        PublicationHolder(View itemView) {
            super(itemView);
            botonComentar = itemView.findViewById(R.id.boton_comentar);
            cantidadComentarios = itemView.findViewById(R.id.cantidad_comentarios);
            cantidadLikes = itemView.findViewById(R.id.cantidad_likes);
            fotoUsuarioActual = itemView.findViewById(R.id.user_imagen);
            fotoUsuario = itemView.findViewById(R.id.foto_usuario_publicacion);
            nombreUsuario = itemView.findViewById(R.id.usuario_publicacion);
            fechaPublicacion = itemView.findViewById(R.id.fecha_publicacion);
            descripcionPublicacion = itemView.findViewById(R.id.descripcion_publicacion);
            imagenPublicacion = itemView.findViewById(R.id.imagen_publicacion);
            comentario = itemView.findViewById(R.id.comentario);
            botonLike = itemView.findViewById(R.id.like_button);

        }
    }

}
