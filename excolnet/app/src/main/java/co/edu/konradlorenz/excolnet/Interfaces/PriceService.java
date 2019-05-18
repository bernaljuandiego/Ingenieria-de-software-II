package co.edu.konradlorenz.excolnet.Interfaces;

import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Ciudad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PriceService {
    String API_ROUTE = "/api/city_prices?api_key=hfkibcktwtxi4f&query=";

    @GET("/api/city_prices")
    Call < Ciudad> getCiudad(@Query("api_key") String api, @Query("query") String query);

}
