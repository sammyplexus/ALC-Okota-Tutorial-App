package tutorials.freelance.networkandinstance;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("api")
    Call<Object> getRandomData();

}
