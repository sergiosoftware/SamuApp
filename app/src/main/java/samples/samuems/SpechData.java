package samples.samuems;


public class SpechData {
       public String datos;
       public String coordenadas;
       public String cedula;

    public SpechData(String datos, String coordenadas,String cedula) {
        this.datos = datos;
        this.coordenadas = coordenadas;
        this.cedula = cedula;
    }

    public SpechData(){

    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getDatos() {
        return datos;
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
}
