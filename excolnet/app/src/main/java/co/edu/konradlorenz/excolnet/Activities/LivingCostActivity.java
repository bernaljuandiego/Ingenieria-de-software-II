package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.excolnet.Adapters.AdapterPrecios;
import co.edu.konradlorenz.excolnet.Entities.Ciudad;
import co.edu.konradlorenz.excolnet.Entities.Precios;
import co.edu.konradlorenz.excolnet.Interfaces.PriceService;
import co.edu.konradlorenz.excolnet.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivingCostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapterPrecios;
    private RecyclerView.LayoutManager mlayoutManagerS;

    List<Precios> listaDePreciosEntity;
    List<String> listaNombrPrecios;
    private ArrayList<String> listaPrecios;
    private Context context;
    private Ciudad ciudad;
    private SearchView searchView;
    private Spinner spinner;
    private ArrayAdapter adapterSpinner;
    private String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_cost);
        context=this;
        selectedItem="Bogota";
        listaDePreciosEntity = new ArrayList<>();
        listaNombrPrecios=new ArrayList<>();
        listaPrecios=new ArrayList<>();
        ciudad = new Ciudad();
        searchView = findViewById(R.id.search_cost);
        spinner = findViewById(R.id.spinner);

        adapterSpinner=ArrayAdapter.createFromResource(this,R.array.city_list,R.layout.item_spinner);
        adapterSpinner.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(adapterSpinner);
       //  arrayAdapter = new ArrayAdapter(this, R.layout.precios,listaPrecios);
        adapterPrecios = new AdapterPrecios(context,listaDePreciosEntity);
        recyclerView=findViewById(R.id.preciosrecycler);

        recyclerView.setAdapter(adapterPrecios);
        getCiudad();



    }



    private void getCiudad() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.numbeo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PriceService priceService = retrofit.create(PriceService.class);
        Call<Ciudad> call = priceService.getCiudad("hfkibcktwtxi4f",selectedItem);
        call.enqueue(new Callback<Ciudad>() {
            @Override
            public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {

                ciudad = response.body();
//https://www.numbeo.com/api/city_prices?api_key%3Dhfkibcktwtxi4f%26query=Bogota
               listaDePreciosEntity = ciudad.getPrices();

                recyclerView =(RecyclerView) findViewById(R.id.preciosrecycler);
                recyclerView.setHasFixedSize(true);
                mlayoutManagerS = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(mlayoutManagerS);
                adapterPrecios = new AdapterPrecios(context,listaDePreciosEntity);
                recyclerView.setAdapter(adapterPrecios);
            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t) {
                Log.w("LIVING_COST",t.getCause()+"---"+t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(searchView!=null){
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem=spinner.getSelectedItem().toString();
                getCiudad();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItem =spinner.getSelectedItem().toString();
                adapterPrecios.notifyDataSetChanged();
            }
        });

    }
    private void search (String newText){
        ArrayList<Precios> precios = new ArrayList<>();
        for(Precios pr: listaDePreciosEntity){
            if(pr.getItem_name().toLowerCase().contains(newText.toLowerCase())
            &&!newText.equals(" ")){
                precios.add(pr);
            }
        }

        recyclerView =(RecyclerView) findViewById(R.id.preciosrecycler);
        recyclerView.setHasFixedSize(true);
        mlayoutManagerS = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mlayoutManagerS);
        adapterPrecios = new AdapterPrecios(context,precios);
        recyclerView.setAdapter(adapterPrecios);
adapterPrecios.notifyDataSetChanged();




    }
}
