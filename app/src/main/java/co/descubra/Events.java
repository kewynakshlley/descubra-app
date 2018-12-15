package co.descubra;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.descubra.adapter.EventAdapter;
import co.descubra.adapter.SwipeAdapter;
import co.descubra.connection.DescubraAPI;
import co.descubra.model.Event;
import co.descubra.model.Token;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Events extends FragmentActivity {
    private Button button;
    private ViewPager viewPager;
    private ListView listViewEvents;
    private List<Event> events;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.button = findViewById(R.id.configFilter);
        this.events = new ArrayList<>();
        this.listViewEvents =  findViewById(R.id.listView);
        events = (List<Event>) getIntent().getSerializableExtra("events");
        System.out.println(events.size());
        populateListView();
       /* this.viewPager = findViewById(R.id.viewPager);

        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);*/

    }

    public void populateListView(){
        this.eventAdapter = new EventAdapter(this, events);
        listViewEvents.setAdapter(eventAdapter);
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Event item = (Event) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), EventDetail.class);
                intent.putExtra("event", item);
                startActivity(intent);
            }


        });
    }
    public void redirectToConfig(View view){
        startActivity(new Intent(this, Config.class));
    }

}
