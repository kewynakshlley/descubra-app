package co.descubra.connection;

import java.util.List;

import co.descubra.model.Event;
import co.descubra.model.Login;
import co.descubra.model.Token;
import co.descubra.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DescubraAPI {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") long id);

    @GET("/events")
    Call<List<Event>> getAllEvents();

    @POST("/users")
    Call<User> addUser(@Body User user);

    @POST("/login")
    Call<Token> authenticate(@Body Login login);

    @GET("/events/nearby_events")
    Call<List<Event>> addContext(@Header("Authorization") String token,
                                 @Query("radius") double radius,
                                 @Query("latitude") double latitude,
                                 @Query("longitude") double longitude);
}
