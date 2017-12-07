package isistemas.umariana.edu.co.zonaswifigratisparalagente.datosApi;

/**
 * Created by felipe on 28/11/17.
 */

import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SitiosService {
    @GET("f4kx-n3nn.json")
    Call <List<ZonasWiFi>> obtenerListadeZonas();
}
