package co.descubra.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import co.descubra.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    private ImageView imageView;
    private TextView titleEvent;
    private ExpandableTextView expandableTextView;
    String longtext =  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,";


    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        this.imageView = view.findViewById(R.id.imageEvent);
        this.titleEvent = view.findViewById(R.id.titleEvent);
        this.expandableTextView = view.findViewById(R.id.expandable);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        titleEvent.setText("Title = " + message);
        expandableTextView.setText(longtext);
        return view;
    }

}
