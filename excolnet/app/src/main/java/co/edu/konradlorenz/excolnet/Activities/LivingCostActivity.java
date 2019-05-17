package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    ArrayList<String> listaPrecios;
    Context context;
    private Ciudad ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_cost);
        context=this;
        listaDePreciosEntity = new ArrayList<>();
        listaNombrPrecios=new ArrayList<>();
        listaPrecios=new ArrayList<>();
        ciudad = new Ciudad();
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
        Call<Ciudad> call = priceService.getCiudad();
        call.enqueue(new Callback<Ciudad>() {
            @Override
            public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {

                ciudad = response.body();

               Log.w("LIVING COST", ciudad.getPrices().toString());
               listaDePreciosEntity = ciudad.getPrices();

               for (Precios list: ciudad.getPrices()){

                   listaPrecios.add(list.getItem_name());
               }
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

}
