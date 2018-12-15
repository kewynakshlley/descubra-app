package co.descubra;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import co.descubra.connection.DescubraAPI;
import co.descubra.model.Event;
import co.descubra.model.Token;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    private FusedLocationProviderClient client;
    private Button avancarButton;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.avancarButton = findViewById(R.id.avancarButton1);
        avancarButton.setOnClickListener(this);
        client = LocationServices.getFusedLocationProviderClient(this);
        this.prefs = getSharedPreferences("coordinates", MODE_PRIVATE);

        requestPermission();


    }

    @Override
    public void onClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        client.getLastLocation().addOnSuccessListener(Welcome.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("latitude", (float) location.getLatitude());
                editor.putFloat("longitude", (float) location.getLongitude());
                editor.putFloat("radius", (float) 10);
                editor.apply();
                System.out.println("COORDENADAS");
                System.out.println(location.getLatitude());
                System.out.println(location.getLongitude());
                prefs = getSharedPreferences("login", MODE_PRIVATE);
                getNearByEvents(prefs.getString("token", ""), 10, location.getLatitude(),location.getLongitude());

            }
        });

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void getNearByEvents(String token, double radius, double latitude, double longitude){
        Retrofit retrofit = ServiceGeneratorUtil.retrofitBuilder(ResourcesUtil.URL_BASE);
        DescubraAPI descubraAPI = retrofit.create(DescubraAPI.class);
        Call<List<Event>> request = descubraAPI.addContext(token, radius, latitude, longitude);
        try {
            List<Event> events = request.execute().body();
            System.out.println("SIZE: "+events.size());
            Intent it = new Intent(this, Events.class);
            it.putExtra("events", (Serializable) events);
            startActivity(it);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




