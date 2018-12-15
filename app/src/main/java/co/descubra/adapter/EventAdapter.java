package co.descubra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.descubra.R;
import co.descubra.model.Event;

public class EventAdapter extends BaseAdapter{
    private Activity context;
    private List<Event> events;
    private static LayoutInflater inflater = null;

    public EventAdapter(Activity context, List<Event> events) {
        this.context = context;
        this.events = events;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.itens_eventos, null): itemView;
        TextView name = itemView.findViewById(R.id.nameEvent);
        TextView date = itemView.findViewById(R.id.dataEvent);
        TextView hour = itemView.findViewById(R.id.hourEvent);
        Event selectedEvent = events.get(position);
        name.setText(selectedEvent.getName());
        date.setText(selectedEvent.getDate());
        hour.setText(selectedEvent.getHour());
        return itemView;
    }
}
