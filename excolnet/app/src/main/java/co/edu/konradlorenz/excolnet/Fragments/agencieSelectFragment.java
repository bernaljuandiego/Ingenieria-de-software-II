package co.edu.konradlorenz.excolnet.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.excolnet.Adapters.agenciesAdapter;
import co.edu.konradlorenz.excolnet.Entities.Agencia;
import co.edu.konradlorenz.excolnet.R;

public class agencieSelectFragment extends Fragment {


    public agencieSelectFragment() {
        // Required empty public constructor
    }

    public RecyclerView agencies;

    public ArrayList<Agencia> agencias;

    public ValueEventListener listener;

    public DatabaseReference myRef;

    public agenciesAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agencie_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    public void initializeComponents(){
    this.agencies = getView().findViewById(R.id.agentList);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        agencies.setLayoutManager(lm);
        agencies.setHasFixedSize(true);
    this.agencias = new ArrayList<>();
    this.myRef = FirebaseDatabase.getInstance().getReference("BaseDatos").child("Agencies");


    listener  = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot snap :  dataSnapshot.getChildren()){
                Agencia  tempAg = new Agencia();
                tempAg.setPhotoLogo(snap.child("PhotoLogo").getValue(String.class));
                tempAg.setNombre(snap.getKey());
                String val = snap.child("telefono").getValue(String.class);
                tempAg.setTelefono(val);

                agencias.add(tempAg);

            }
            adapter =  new agenciesAdapter(getContext() , agencias);
            agencies.setAdapter(adapter);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    }

    @Override
    public void onResume() {
        super.onResume();
        myRef.addValueEventListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        myRef.removeEventListener(listener);
    }



}
