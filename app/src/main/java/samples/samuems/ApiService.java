package samples.samuems;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/samuapp/script.php")
    Call<Void> getDatos (@Query("datos") String datos);

    @POST("/samuapp/script.php")
    Call<SpechData> getDatosPost (@Field("datos") String datos);

}
