package co.descubra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import co.descubra.connection.DescubraAPI;
import co.descubra.model.Event;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Retrofit;

public class Config extends AppCompatActivity {
    private Button update;
    private static SeekBar seekBar;
    private static TextView kmText;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getSupportActionBar().hide();
        this.prefs = getSharedPreferences("coordinates", MODE_PRIVATE);
        this.update = findViewById(R.id.button);
        seekBar();

    }

    public void seekBar(){
        seekBar = findViewById(R.id.seekBar3);
        seekBar.setMax(100);
        seekBar.setProgress(10);
        kmText = findViewById(R.id.kmTxt);
        kmText.setText(seekBar.getProgress()+" km");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressBarValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressBarValue = progress;
                kmText.setText(progress+" km(s)");
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("radius", (float) progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                kmText.setText(progressBarValue+" km(s)");
            }
        });
    }

    public void update(View view){
        this.prefs = getSharedPreferences("coordinates", MODE_PRIVATE);
        float latitude = prefs.getFloat("latitude", 0.0f);
        float longitude = (prefs.getFloat("longitude", 0.0f));
        float radius = (prefs.getFloat("radius", 0.0f));
        this.prefs = getSharedPreferences("login", MODE_PRIVATE);
        String token = prefs.getString("token", "");
        System.out.println(""+ latitude+" "+longitude+" "+radius);
        getNearByEvents(token, radius, latitude, longitude);


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
