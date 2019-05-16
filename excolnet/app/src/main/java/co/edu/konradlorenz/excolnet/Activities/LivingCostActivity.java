package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Ciudad;
import co.edu.konradlorenz.excolnet.Interfaces.PriceService;
import co.edu.konradlorenz.excolnet.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivingCostActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> precios;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_cost);
        precios = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,R.layout.activity_living_cost,precios);
        listView=findViewById(R.id.precios);

        listView.setAdapter(arrayAdapter);
        getCiudad();
    }

    private void getCiudad() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.numbeo.com//api/city_prices?api_key=hfkibcktwtxi4f")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PriceService priceService = retrofit.create(PriceService.class);
        Call<List<Ciudad>> call = priceService.getCiudad();
        call.enqueue(new Callback<List<Ciudad>>() {
            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                for(Ciudad ciudad : response.body()){
                    precios.add(ciudad.getPrices().getItem_name());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {

            }
        });
    }
}
