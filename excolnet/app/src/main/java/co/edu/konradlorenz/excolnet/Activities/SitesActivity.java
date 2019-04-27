package co.edu.konradlorenz.excolnet.Activities;

import androidx.fragment.app.FragmentActivity;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SitesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private double latitud;
    private double longitud;
    private String titulo="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitud =4.6420828;
         longitud =-78.8355855;

        String id=getIntent().getExtras().getString("id");
        Double lat= getIntent().getExtras().getDouble("latitud");
         Double lon = getIntent().getExtras().getDouble("longitud");
         titulo =getIntent().getExtras().getString("titulo");

         if(lat !=null && lon!=null){
             latitud=lat;
             longitud=lon;
         }

        firebaseLoadData();

    }

    private void firebaseLoadData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng site = new LatLng(latitud, longitud);

        mMap.addMarker(new MarkerOptions().position(site).title("Marker in "+titulo));

        float zoomLevel = (float)16.0f;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(site,zoomLevel));
    }
}
