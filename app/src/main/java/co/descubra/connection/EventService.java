package co.descubra.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.descubra.model.Event;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Retrofit;

public class EventService {

    public List<Event> getEvents(){
        List<Event> eventList = new ArrayList<>();
        Retrofit retrofit = ServiceGeneratorUtil.retrofitBuilder(ResourcesUtil.URL_BASE);
        DescubraAPI descubraAPI = retrofit.create(DescubraAPI.class);
        Call<List<Event>> request = descubraAPI.getAllEvents();
        try {
            eventList = request.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
        return eventList;
    }


}
