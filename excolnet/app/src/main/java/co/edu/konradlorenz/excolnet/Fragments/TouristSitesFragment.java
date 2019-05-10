package co.edu.konradlorenz.excolnet.Fragments;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Adapters.SiteAdapter;
import co.edu.konradlorenz.excolnet.Entities.Lugar;
import co.edu.konradlorenz.excolnet.R;


public class TouristSitesFragment extends Fragment {

    private RecyclerView itemSites;
    private RecyclerView.Adapter mAdapterS;
    private RecyclerView.LayoutManager mlayoutManagerS;
    private DatabaseReference baseDatos;
    private ArrayList<Lugar> lugares;
    private ValueEventListener listener;
    private FirebaseUser user;


    public TouristSitesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tourist_sites, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseDatos = FirebaseDatabase.getInstance().getReference("BaseDatos");
        lugares = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lugares.clear();

                for (DataSnapshot asistenteSnapshot : dataSnapshot.getChildren()) {
                    Lugar lugar = asistenteSnapshot.getValue(Lugar.class);
                    lugares.add(lugar);
                }
                itemSites = (RecyclerView) getView().findViewById(R.id.sites);
                itemSites.setHasFixedSize(true);

                mlayoutManagerS = new LinearLayoutManager(getContext());
                itemSites.setLayoutManager(mlayoutManagerS);

                mAdapterS = new SiteAdapter(getContext(), lugares);
                itemSites.setAdapter(mAdapterS);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }

        };
        baseDatos.child("Lugares").addValueEventListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        baseDatos.child("Lugares").removeEventListener(listener);
    }
}
