package co.descubra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import co.descubra.model.Event;

public class EventDetail extends AppCompatActivity {
    private TextView textView5;
    private TextView textView4;
    private TextView textView3;
    private TextView textView2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        getSupportActionBar().hide();
        this.textView5 = findViewById(R.id.textView5);
        this.textView4 = findViewById(R.id.textView4);
        this.textView3 = findViewById(R.id.textView3);
        this.textView2 = findViewById(R.id.textView2);
        this.textView = findViewById(R.id.textView);
        Event event = (Event) getIntent().getSerializableExtra("event");
        textView5.setText(event.getName());
        textView4.setText(event.getDescription());
        textView3.setText(event.getCategory());
        textView2.setText(String.valueOf(event.getLatitude()));
        textView.setText(String.valueOf(event.getLongitude()));

    }
}
