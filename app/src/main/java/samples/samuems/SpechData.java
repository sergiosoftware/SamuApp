package samples.samuems;


public class SpechData {
        public String cedulaPaciente;
        public String texto;
        public String lat;
        public String lon;


    public SpechData(){

    }

    public SpechData(String cedulaPaciente, String texto, String lat, String lon) {
        this.cedulaPaciente = cedulaPaciente;
        this.texto = texto;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
