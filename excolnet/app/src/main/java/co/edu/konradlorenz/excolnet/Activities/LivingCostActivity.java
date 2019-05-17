package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
    ListView listView;
    ArrayList<String> precios;
    ArrayAdapter arrayAdapter;
    List<Precios> listaDePreciosEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_cost);
        precios = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, R.layout.precios, precios);
        listView=findViewById(R.id.precios);
        listaDePreciosEntity = new ArrayList<Precios>();
        listView.setAdapter(arrayAdapter);
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

                    listaDePreciosEntity= response.body().getPrices();
                for(Precios str:listaDePreciosEntity){
                    precios.add(str.getItem_name());
                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t) {
                Log.w("LIVING_COST",t.getCause()+"---"+t.getMessage());
            }
        });
    }
}
