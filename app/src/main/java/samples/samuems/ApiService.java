package samples.samuems;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/samuapp/script.php")
    Call<ResponseBody> getDatos (@Query("datos") String datos);

    @POST("/samuapp/script.php")
    Call<Void> datosPost (@Body SpechData datos);

}
