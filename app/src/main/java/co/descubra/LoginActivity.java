package co.descubra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.descubra.connection.DescubraAPI;
import co.descubra.model.Event;
import co.descubra.model.Login;
import co.descubra.model.Token;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button btnEnter;
    private Button btnRegister;
    private String token;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        this.usernameEdt = findViewById(R.id.emailLogin);
        this.passwordEdt = findViewById(R.id.senhaLogin);
        this.btnEnter = findViewById(R.id.entrarButton);
        btnEnter.setOnClickListener(this);
        this.btnRegister = findViewById(R.id.btnCadastrarFromLogin);
        btnRegister.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.entrarButton:
                validateUserToLogin(String.valueOf(usernameEdt.getText()), String.valueOf(passwordEdt.getText()));
                break;
            case R.id.btnCadastrarFromLogin:
                startActivity(new Intent(this, Register.class));
        }
    }

    public void validateUserToLogin(final String username, final String password) {

        Login login = new Login(username, password);
        Retrofit retrofit = ServiceGeneratorUtil.retrofitBuilder(ResourcesUtil.URL_BASE);
        DescubraAPI descubraAPI = retrofit.create(DescubraAPI.class);
        Call<Token> request = descubraAPI.authenticate(login) ;
        request.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    token = response.body().getToken();
                    successful(username, password, token);
                    Log.i("TOKEN", token);
                }else{
                    Toast.makeText(getApplicationContext(), "Login incorreto!", Toast.LENGTH_SHORT)                     .show();
                }


            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast                            .LENGTH_SHORT).show();
            }
        });

    }

    private void successful(String username, String password, String token){
        storeUserLoginOnSharedPreferences(username, password, token);
        startActivity(new Intent(this, Welcome.class));
        finish();
    }

    private void storeUserLoginOnSharedPreferences(String username, String password, String token) {
        SharedPreferences.Editor s = sharedPreferences.edit();
        s.putString("username", username);
        s.putString("password", password);
        s.putString("token", token);
        s.commit();


    }
}
