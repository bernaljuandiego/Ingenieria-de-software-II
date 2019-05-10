package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.konradlorenz.excolnet.Adapters.CommentAdapter;
import co.edu.konradlorenz.excolnet.Entities.Comentario;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

public class DetailPublicationActivity extends AppCompatActivity {

    Publicacion publicacion;
    EditText comentario;
    Button botonComentar;
    private RecyclerView items;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView userName;
    private TextView publicationDate;
    private TextView publicationDescription;
    private Usuario publicationUser;
    private ImageView actualUserImage;
    private ImageView userImage;
    private ImageView publicationImage;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private ValueEventListener lisener;

    private TextView cantidadComentarios;
    private TextView cantidadLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        firebaseLoadData();
        setUpToolbar();
        findMaterialElements();


        setUpLayoutData();
    }

    private void setUpToolbar() {
        String[] firebaseName = user.getDisplayName().split(" ");
        String userName = "@";

        for (int i = 0; i < firebaseName.length; i++) {
            if (i == (firebaseName.length - 1)) {
                userName += firebaseName[i];
            } else {
                userName += firebaseName[i] + "_";
            }
        }

        getSupportActionBar().setTitle(userName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void firebaseLoadData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
    }

    private void setUpLayoutData() {


        String id = getIntent().getExtras().getString("id");
        mDatabase.child("Publicaciones").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                publicacion = dataSnapshot.getValue(Publicacion.class);
                Glide.with(getApplicationContext()).load(publicacion.getUsuario().getPhotoUrl()).into(userImage);
                Glide.with(getApplicationContext()).load(publicacion.getImagen()).into(publicationImage);

                Glide.with(getApplicationContext()).load(user.getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).fitCenter().apply(RequestOptions.circleCropTransform()).into(actualUserImage);
                userName.setText(publicacion.getUsuario().getDisplayName());
                publicationDate.setText(publicacion.getFechaPublicacion());
                publicationDescription.setText(publicacion.getTexto());
                try {
                    cantidadLikes.setText(publicacion.getUsuariosQueGustan().size() + " Likes");
                    cantidadComentarios.setText(publicacion.getComentarios().size() + " Comments");
                } catch (NullPointerException e) {
                }

                final ArrayList<Comentario> comentarios = publicacion.getComentarios();
                Log.e("holi", "" + comentarios.size());
                items = (RecyclerView) findViewById(R.id.recicler_comentarios);
                items.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                items.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                mAdapter = new CommentAdapter(comentarios, getApplicationContext());
                items.setAdapter(mAdapter);

                botonComentar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (comentario.getText().toString() != "") {
                            String pattern = "yyyy-MM-dd";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            String date = simpleDateFormat.format(new Date());
                            Usuario newUser = new Usuario(user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString(), user.getUid());
                            publicacion.getComentarios().add(new Comentario(newUser, comentario.getText().toString(), date));
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
                            mDatabase.child("Publicaciones").child(publicacion.getId()).setValue(publicacion);
                            comentario.setText("");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void findMaterialElements() {
        botonComentar = findViewById(R.id.boton_comentar);
        comentario = findViewById(R.id.comentario);
        actualUserImage = findViewById(R.id.user_imagen);
        userName = findViewById(R.id.usuario_publicacion);
        userImage = findViewById(R.id.foto_usuario_publicacion);
        publicationDate = findViewById(R.id.fecha_publicacion);
        publicationDescription = findViewById(R.id.descripcion_publicacion);
        publicationImage = findViewById(R.id.imagen_publicacion);
        cantidadComentarios = findViewById(R.id.cantidad_comentarios);
        cantidadLikes = findViewById(R.id.cantidad_likes);
    }


}
