package co.descubra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.descubra.model.User;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button firstAvancarButton;
    private EditText nameEdt;
    private EditText passwordEdt;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        this.firstAvancarButton = findViewById(R.id.avancarButtonRegister);
        firstAvancarButton.setOnClickListener(this);
        this.nameEdt = findViewById(R.id.nomeRegister);
        this.passwordEdt = findViewById(R.id.passwordRegister);
        this.userName = findViewById(R.id.userName);
    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(this, RegisterTwo.class);
        User user = new User();
        user.setName(String.valueOf(nameEdt.getText()));
        user.setPassword(String.valueOf(passwordEdt.getText()));
        user.setUsername(String.valueOf(userName.getText()));
        it.putExtra("userInfo", user);
        startActivity(it);
    }
}
