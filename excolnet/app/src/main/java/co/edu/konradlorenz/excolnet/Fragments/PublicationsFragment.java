package co.edu.konradlorenz.excolnet.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import co.edu.konradlorenz.excolnet.Adapters.PublicationAdapter;
import co.edu.konradlorenz.excolnet.Entities.Publicacion;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

public class PublicationsFragment extends Fragment {
    private RecyclerView items;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference baseDeDatos;
    private ArrayList<Publicacion> publicaciones;
    private ValueEventListener lisener;
    private FirebaseUser user;
    private Usuario usuario;

    @SuppressLint("ValidFragment")
    public PublicationsFragment(Usuario user) {
        this.usuario = user;
    }

    public PublicationsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseDeDatos = FirebaseDatabase.getInstance().getReference("BaseDatos");
        publicaciones = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        lisener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                publicaciones.clear();

                for (DataSnapshot asistenteSnapshot : snapshot.getChildren()) {
                    if (usuario == null) {
                        Publicacion publicacion = asistenteSnapshot.getValue(Publicacion.class);
                        publicaciones.add(publicacion);
                    } else {
                        Publicacion publicacion = asistenteSnapshot.getValue(Publicacion.class);
                        if (publicacion.getUsuario().getDisplayName().equals(usuario.getDisplayName())) {
                            publicaciones.add(publicacion);
                        }
                    }
                }

                items = (RecyclerView) getView().findViewById(R.id.lista_publicaciones);
                items.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(getContext());
                items.setLayoutManager(mLayoutManager);
                Collections.reverse(publicaciones);

                // specify an adapter (see also next example)
                mAdapter = new PublicationAdapter(getContext(), publicaciones, user);
                items.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: ", firebaseError.getMessage());
            }
        };
        Query myLastPost = baseDeDatos.child("Publicaciones").orderByChild("fechaPublicacion");
        myLastPost.addValueEventListener(lisener);


    }

    @Override
    public void onPause() {
        super.onPause();
        baseDeDatos.child("Publicaciones").removeEventListener(lisener);
    }
}
