package tutorials.freelance.networkandinstance;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView mNetworkResponse;
    ProgressDialog progressDialog;

    Retrofit retrofit;

    //Server name is https://randomuser.me/api
    //Using retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetworkResponse = findViewById(R.id.networkResponse);

        progressDialog = new ProgressDialog(this);
        //To prevent the user from dismissing the dialog
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading data from the network");

        progressDialog.show();



        Gson gson = new Gson();
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://randomuser.me/").build();

        Service myService = retrofit.create(Service.class);

        myService.getRandomData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.hide();
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                String responseFromNetwork = response.body().toString();
                mNetworkResponse.setText(responseFromNetwork);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

    });
    }

}
