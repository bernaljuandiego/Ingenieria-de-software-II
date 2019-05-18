package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import co.edu.konradlorenz.excolnet.Adapters.NocturneLifeAdapter;
import co.edu.konradlorenz.excolnet.Entities.Interes;
import co.edu.konradlorenz.excolnet.R;

public class NocturneLifeActivity extends AppCompatActivity {

    private RecyclerView  nocturneList;
    private Toolbar  toolbar;
    private FirebaseAuth  myAuth;
    private DatabaseReference dataRef;
    private DatabaseReference database;
    private ArrayList<Interes> intereses;
    private NocturneLifeAdapter adapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocturne_life);
        intereses = new ArrayList<>();
        getViewElements();
        getFirebaseComponents();
        initializeRecyclerView();


    }

    public void initializeRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        this.nocturneList.setHasFixedSize(true);
        this.nocturneList.setLayoutManager(manager);
    }
    public void getViewElements(){
        this.nocturneList = findViewById(R.id.nocturneList);
        this.toolbar = findViewById(R.id.toolbarNocturneLife);
        this.toolbar.setTitle("Nightlife");

    }

    public void getFirebaseComponents(){
        this.myAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference("BaseDatos");
        this.dataRef =  database.child("NocturneLife");

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){


        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("NocLife", "onDataChange bruh: " +  dataSnapshot.getChildrenCount());
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String tempTopicType = data.getKey();
                    for(DataSnapshot data2 : data.getChildren()){
                        Interes interes = data2.getValue(Interes.class);
                        interes.setTopicName(data2.getKey());
                        interes.setTopicType(tempTopicType);
                        intereses.add(interes);

                    }
                }
                Collections.shuffle(intereses);
                adapt = new NocturneLifeAdapter(intereses , getApplicationContext());
                nocturneList.setAdapter(adapt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }






}
