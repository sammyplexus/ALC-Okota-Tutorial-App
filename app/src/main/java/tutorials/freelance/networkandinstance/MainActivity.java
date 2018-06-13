package tutorials.freelance.networkandinstance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText mPhoneNumber, mPhoneNumber2;

    Retrofit retrofit;

    //Server name is https://randomuser.me/api
    //Using retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhoneNumber = findViewById(R.id.phoneNumber);

        mPhoneNumber2 = findViewById(R.id.phoneNumber2);

        mPhoneNumber2.setText("Hello world");

        if (savedInstanceState != null){
            mPhoneNumber2.setText("Hello world");
            final String phoneNumberEnteredTheOtherTime = savedInstanceState.getString("phone_number");
            Toast.makeText(this, phoneNumberEnteredTheOtherTime, Toast.LENGTH_LONG).show();

            mPhoneNumber2.setText(phoneNumberEnteredTheOtherTime);
        }

        Gson gson = new Gson();
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://randomuser.me/").build();

        Service myService = retrofit.create(Service.class);

        myService.getRandomData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

    });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String phoneNumberEntered = mPhoneNumber.getText().toString();

        outState.putString("phone_number", phoneNumberEntered);

    }
}
