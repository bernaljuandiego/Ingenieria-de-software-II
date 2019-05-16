package co.edu.konradlorenz.excolnet.Interfaces;

import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Ciudad;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PriceService {
    String API_ROUTE = "&query=Bogota";

    @GET
    Call<List<Ciudad>> getCiudad();
}
