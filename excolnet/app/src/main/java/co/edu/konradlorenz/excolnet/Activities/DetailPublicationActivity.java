package co.edu.konradlorenz.excolnet.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.Adapters.PublicationAdapter;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DetailPublicationActivity extends AppCompatActivity {


    private TextView userName;
    private TextView publicationDate;
    private TextView publicationDescription;
    private Usuario publicationUser;
    private ImageView userImage;
    private ImageView publicationImage;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private ValueEventListener lisener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        firebaseLoadData();
        setUpToolbar();
        findMaterialElements();

        setUpLayoutData();
    }

    private void setUpToolbar(){
        String[] firebaseName = user.getDisplayName().split(" ");
        String userName = "@";

        for(int i = 0; i < firebaseName.length; i++){
            if (i == (firebaseName.length - 1)) {
                userName += firebaseName[i];
            }else{
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

    private void setUpLayoutData(){
        String id = getIntent().getExtras().getString("id");
        Log.e("id",id);
        mDatabase.child("Publicaciones").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("holi","holi");
                Publicacion publicacion = dataSnapshot.getValue(Publicacion.class);
                userName.setText(publicacion.getUsuario().getDisplayName());
                publicationDate.setText(publicacion.getFechaPublicacion());
                publicationDescription.setText(publicacion.getTexto());
                Glide.with(getApplicationContext()).load(publicacion.getUsuario().getPhotoUrl()).into(userImage);
                Glide.with(getApplicationContext()).load(publicacion.getImagen()).into(publicationImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void findMaterialElements(){
        userName = findViewById(R.id.usuario_publicacion);
        userImage = findViewById(R.id.foto_usuario_publicacion);
        publicationDate = findViewById(R.id.fecha_publicacion);
        publicationDescription = findViewById(R.id.descripcion_publicacion);
        publicationImage = findViewById(R.id.imagen_publicacion);
    }


}
