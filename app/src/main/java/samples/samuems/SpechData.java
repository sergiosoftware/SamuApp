package samples.samuems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpechData {
    @SerializedName("datos")
    @Expose
    private String datos;

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getDatos() {
        return datos;
    }
}
