package co.edu.konradlorenz.excolnet.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.Fragments.NewPuplicationFragment;
import androidx.appcompat.widget.Toolbar;
import co.edu.konradlorenz.excolnet.Fragments.BottomSheetNavigationFragment;
import co.edu.konradlorenz.excolnet.Fragments.PublicationsFragment;
import co.edu.konradlorenz.excolnet.R;
import co.edu.konradlorenz.excolnet.Utils.AdapterSearch;
import co.edu.konradlorenz.excolnet.Utils.Permissions;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {


    private static final int VERIFY_PERMISSIONS_REQUEST = 1;
    private BottomAppBar bottomAppBar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ArrayList<Usuario> listaUsuarios;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        findLayoutElements();
        setUpBottomBar();

        mAuth = FirebaseAuth.getInstance();

        if (checkPermissionsArray(Permissions.PERMISSIONS)) {

        } else {
            verifyPermissions(Permissions.PERMISSIONS);
        }

        BottomAppBar bottomAppBar = findViewById(R.id.app_bar_publications);
        //Search
        mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
        recyclerView=findViewById(R.id.recyclerViewSearch);
        searchView = findViewById(R.id.searchView);

        //Fragment fragment = new PublicationsFragment();
        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.contenido, fragment);
        //ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference usuarios = mDatabase.child("Users");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listaUsuarios = new ArrayList<>();
                    for (DataSnapshot dato : dataSnapshot.getChildren()) {
                        listaUsuarios.add(dato.getValue(Usuario.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(searchView !=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }
    private void search(String str){
        ArrayList<Usuario> myListUsuarios = new ArrayList<>();
        for (Usuario usuarioBuscado: listaUsuarios ) {
            if (usuarioBuscado.getDisplayName().toLowerCase().contains(str.toLowerCase())){
                myListUsuarios.add(usuarioBuscado);
            }
        }
        AdapterSearch adapterSearch = new AdapterSearch(getApplicationContext(),myListUsuarios );

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterSearch);
    }

    public void findLayoutElements() {
        bottomAppBar = findViewById(R.id.app_bar_publications);
    }

    public void setUpBottomBar() {

        setSupportActionBar(bottomAppBar);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.app_bar_home:
                        Fragment fragment = new PublicationsFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contenido, fragment);
                        ft.commit();
                        break;
                    case R.id.app_bar_notifications:
                        Intent intent = new Intent(PrincipalActivity.this, DetailPublicationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(PrincipalActivity.this, "Notifications Icon Pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app_bar_profile:
                        mAuth.signOut();
                        LoginManager.getInstance().logOut();
                        Intent newintent = new Intent(PrincipalActivity.this, LoginActivity.class);
                        newintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newintent);
                        break;
                }
                return false;
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_publications);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewPuplicationFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.principal, fragment);
                ft.commit();
            }
        });
    }

    public void toggleFabMode(View view) {
        //check the fab alignment mode and toggle accordingly
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_menu, menu);
        return true;
    }

    public void verifyPermissions(String[] permissions) {

        ActivityCompat.requestPermissions(
                PrincipalActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    public boolean checkPermissionsArray(String[] permissions) {

        for (int i = 0; i < permissions.length; i++) {
            String check = permissions[i];
            if (!checkPermissions(check)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPermissions(String permission) {

        int permissionRequest = ActivityCompat.checkSelfPermission(PrincipalActivity.this, permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
}
