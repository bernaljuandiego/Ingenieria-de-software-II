package co.edu.konradlorenz.excolnet.Interfaces;

import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Ciudad;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PriceService {
    String API_ROUTE = "/api/city_prices?api_key=hfkibcktwtxi4f&query=Bogota";

    @GET(API_ROUTE)
    Call < Ciudad> getCiudad();
}
