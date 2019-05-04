package co.edu.konradlorenz.excolnet.Activities;

import androidx.fragment.app.FragmentActivity;

import co.edu.konradlorenz.excolnet.Entities.Host;
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

import java.util.ArrayList;
import java.util.List;

public class SitesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private double latitud;
    private double longitud;
    private String titulo="";
    private String nameActivityEntrante;
    private double[] latitudes;
    private double[] longitudes;
    private List<Host> sitios;



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
        nameActivityEntrante =getIntent().getExtras().getString("nameActivity");

        if(nameActivityEntrante.equals("Housing")){

            sitios = (ArrayList<Host>) getIntent().getSerializableExtra("Hosts");
        }


         if(lat !=null && lon!=null){
             latitud=lat;
             longitud=lon;
         }
         if(titulo==null){
             titulo ="";
         }

        firebaseLoadData();

    }

    private void firebaseLoadData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("BaseDatos");

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(nameActivityEntrante.equals("Housing")){
            for(Host sitio : sitios){
                mMap.addMarker(new MarkerOptions().position(new LatLng(sitio.getLatitud() , sitio.getLongitud())).title(sitio.getNombreHost()));
            }
            /*
            LatLng site = new LatLng(4.6724736, -74.0477403);
            LatLng site2 = new LatLng(4.66081, -74.0495811);
            LatLng site3 = new LatLng(4.7121263, -74.1267447);


            mMap.addMarker(new MarkerOptions().position(site).title("Apartamento en Chico Reservado"));
            mMap.addMarker(new MarkerOptions().position(site2).title("Altos del Nogal Pijao"));
            mMap.addMarker(new MarkerOptions().position(site3).title("Montecarlo 4 Villas"));
            */

            float zoomLevel = (float)6.0f;

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sitios.get(0).getLatitud() , sitios.get(0).getLongitud()),zoomLevel));
        }else{




        LatLng site = new LatLng(latitud, longitud);

        mMap.addMarker(new MarkerOptions().position(site).title(titulo));

        float zoomLevel = (float)16.0f;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(site,zoomLevel));
    }
    }
}
