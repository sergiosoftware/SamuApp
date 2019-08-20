package samples.samuems;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class datos {

    @SerializedName("datos")
    @Expose
    private String datos;
    @SerializedName("coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("cedula")
    @Expose
    private String cedula;

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    @Override
    public String toString() {
        return "Datos{" +
                "datos='" + datos+ '\'' +
                ", coordenadas='" + coordenadas+ '\'' +
                ", cedula=" + cedula+
                '}';
    }
}
