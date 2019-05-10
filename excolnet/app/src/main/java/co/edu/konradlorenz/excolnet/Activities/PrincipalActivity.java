package co.edu.konradlorenz.excolnet.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Adapters.AdapterSearch;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.Fragments.BottomSheetNavigationFragment;
import co.edu.konradlorenz.excolnet.Fragments.NewPuplicationFragment;
import co.edu.konradlorenz.excolnet.Fragments.PublicationsFragment;
import co.edu.konradlorenz.excolnet.R;
import co.edu.konradlorenz.excolnet.Utils.Permissions;

public class PrincipalActivity extends AppCompatActivity {

    private static final int VERIFY_PERMISSIONS_REQUEST = 1;
    private String ACTIVITY_NAME = "PrincipalActivity";
    private BottomAppBar bottomAppBar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private ArrayList<Usuario> listaUsuarios;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fabPublications;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private AdapterSearch adapterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        findMaterialElements();
        setSupportActionBar(bottomAppBar);
        fabPublicationsHandler();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (checkPermissionsArray(Permissions.PERMISSIONS)) {

        } else {
            verifyPermissions(Permissions.PERMISSIONS);
        }


        //Search
        mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");
        recyclerView = findViewById(R.id.recyclerViewSearch);
        searchView = findViewById(R.id.searchView);

        Fragment fragment = new PublicationsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contenido, fragment);
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        listaUsuarios = new ArrayList<>();
        DatabaseReference usuarios = mDatabase.child("Users");
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dato : dataSnapshot.getChildren()) {
                        listaUsuarios.add(dato.getValue(Usuario.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (searchView != null) {
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

    private void search(String str) {
        ArrayList<Usuario> myListUsuarios = new ArrayList<>();
        for (Usuario usuarioBuscado : listaUsuarios) {
            if (usuarioBuscado.getDisplayName().toLowerCase().contains(str.toLowerCase()) && !str.equals("") && !str.equals(" ")) {
                myListUsuarios.add(usuarioBuscado);
            }
        }
        adapterSearch = new AdapterSearch(getApplicationContext(), myListUsuarios);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterSearch);
    }

    public void findMaterialElements() {
        bottomAppBar = findViewById(R.id.app_bar_publications);
        fabPublications = findViewById(R.id.fab_publications);
    }

    //Maneja el botón central flotante de agregar publicaciones.
    private void fabPublicationsHandler() {
        fabPublications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewPuplicationFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.principal, fragment);
                ft.commit();
            }
        });
    }

    //Maneja las opciones del menú del Bottom App Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_home:
                Fragment fragment = new PublicationsFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.contenido, fragment);
                ft.commit();
                break;
            case R.id.app_bar_notifications:
                Toast.makeText(PrincipalActivity.this, "Notifications Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_profile:
                Intent newintent = new Intent(PrincipalActivity.this, ProfileActivity.class);
                newintent.putExtra("ACTIVITY_CALLED_NAME", ACTIVITY_NAME);


                newintent.putExtra("USER", obtenerUsuario());
                startActivity(newintent);
                break;
            case android.R.id.home:
                BottomSheetNavigationFragment bottomNavigationDrawerFragment = new BottomSheetNavigationFragment();
                bottomNavigationDrawerFragment.show(getSupportFragmentManager(), bottomNavigationDrawerFragment.getTag());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Usuario obtenerUsuario() {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getDisplayName().equals(user.getDisplayName())) {
                return usuario;
            }
        }
        return null;
    }

    // Ejecuta el efecto del Bottom App Bar
    public void runFabEffect(View view) {
        //check the fab alignment mode and toggle accordingly
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }

    // Llena el Bottom App Bar con los 3 íconos disponibles.
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

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}