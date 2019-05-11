package co.edu.konradlorenz.excolnet.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import co.edu.konradlorenz.excolnet.Activities.SitesActivity;
import co.edu.konradlorenz.excolnet.Adapters.HostAdapter;
import co.edu.konradlorenz.excolnet.Entities.Host;
import co.edu.konradlorenz.excolnet.R;

public class HousingFragment extends Fragment {
    private RecyclerView itemHosts;
    private RecyclerView.Adapter mAdapterH;
    private RecyclerView.LayoutManager mlayoutManagerH;
    private DatabaseReference baseDatos;
    private ArrayList<Host> hosts;
    private ValueEventListener listener;
    private FirebaseUser user;
    private Button hostButton;
    private ArrayList<Double> latitudes;
    private ArrayList<Double> longitudes;
    private View view;


    public HousingFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_housing, container, false);
        hostButton = view.findViewById(R.id.buttonHousting);
        buttonHandler();
        return view;
    }

    private void buttonHandler() {
        hostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SitesActivity.class);
                intent.putExtra("nameActivity", "Housing");
                intent.putExtra("Hosts", hosts);
                view.getContext().startActivity(intent);


            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseDatos = FirebaseDatabase.getInstance().getReference("BaseDatos");
        hosts = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }


    @Override
    public void onResume() {
        super.onResume();
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hosts.clear();

                for (DataSnapshot asistenteSnapshot : dataSnapshot.getChildren()) {
                    Host host = asistenteSnapshot.getValue(Host.class);
                    hosts.add(host);
                }
                itemHosts = (RecyclerView) getView().findViewById(R.id.recyclerHousting);
                itemHosts.setHasFixedSize(true);

                mlayoutManagerH = new LinearLayoutManager(getContext());
                itemHosts.setLayoutManager(mlayoutManagerH);

                mAdapterH = new HostAdapter(getContext(), hosts);
                itemHosts.setAdapter(mAdapterH);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }

        };
        baseDatos.child("Hosts").addValueEventListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        baseDatos.child("Hosts").removeEventListener(listener);
    }

}
