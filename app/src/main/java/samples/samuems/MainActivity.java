package samples.samuems;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mEntradaVoz;
    private ImageButton mBotonHablar;
    private Retrofit retrofit;
    ApiService service;
    private String coordenadas;
    private String lat;
    private String lon;
    private EditText cedulaPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permisionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permisionCheck== PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }

        }
        cedulaPaciente =findViewById(R.id.editCedula);
        mEntradaVoz = findViewById(R.id.textoEntrada);
        mBotonHablar = findViewById(R.id.botonHablar);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://frozen-escarpment-13524.herokuapp.com")
                //.baseUrl("http://192.168.1.15")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(ApiService.class);
        mBotonHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVoz();
            }
        });
    }

    private void iniciarEntradaVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, value:"En que te puedo ayudar?"):
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {

        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //--> Aqui coordenadas
        LocationManager locManager;
        Location loc;
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        coordenadas=loc.getLongitude()+ " - "+loc.getLatitude();
        lat=""+loc.getLatitude();
        lon=""+loc.getLongitude();
        //System.out.println("Longitud: "+loc.getLongitude()+ " Latitud: "+loc.getLatitude());
        //Toast toast1 = Toast.makeText(getApplicationContext(),"Coordenadas:"+loc.getLongitude()+" - "+loc.getLatitude(), Toast.LENGTH_LONG);
        //toast1.show();


        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mEntradaVoz.setText(result.get(0));

                    //Para Enviar los datos
                    System.out.println(result.get(0));
                    //enviarServidorGet(result.get(0));
                    enviarServidorPost(result.get(0),lat,lon,cedulaPaciente.getText().toString());
}
                break;
            }
        }
    }
    public void enviarServidorGet(String data){
        Call<ResponseBody> response = service.getDatos(data);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null){
                        try {
                            String respuesta= response.body().string();
                            System.out.println("datos de body: "+respuesta);
                            Toast toast1 = Toast.makeText(getApplicationContext(),"Respuesta Servidor:"+respuesta, Toast.LENGTH_LONG);
                            toast1.show();
                        } catch (IOException e) {
                            Toast toast1 = Toast.makeText(getApplicationContext(),"Error en el body", Toast.LENGTH_SHORT);
                            toast1.show();
                        }

                } else {
                    Toast toast2 = Toast.makeText(getApplicationContext(),"Error de Comunicación", Toast.LENGTH_SHORT);
                    toast2.show();
                }
            }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast3 = Toast.makeText(getApplicationContext(),"Error con el Servidor", Toast.LENGTH_SHORT);
                toast3.show();
            }
        });
    }

    public void enviarServidorPost(final String datos, final String latitud,final String longitud, final String cedula){

        SpechData info = new SpechData(cedula,datos,latitud,longitud);
        Call<ResponseBody> response = service.datosPost(info);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    SpechData spechData = new SpechData();
                    //spechData= response.body();
                    //System.out.println("texto de body post: "+spechData);
                    Toast.makeText(getApplicationContext(),"Datos enviados", Toast.LENGTH_LONG).show();
                    System.out.println(response.body().toString());
                } else {
                    Toast.makeText(getApplicationContext(),"La cédula no se encuentra registrada.", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error de Conexión", Toast.LENGTH_LONG).show();

            }
        });


    }
}
