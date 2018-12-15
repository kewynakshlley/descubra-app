package co.descubra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.descubra.connection.UserService;
import co.descubra.model.User;

public class RegisterTwo extends AppCompatActivity implements View.OnClickListener{
    private Button secondAvancarButton;
    private EditText dataEdt;
    private EditText emailEdt;
    private UserService userService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);
        getSupportActionBar().hide();
        this.secondAvancarButton = findViewById(R.id.avancarButtonRegisterTwo);
        secondAvancarButton.setOnClickListener(this);
        this.dataEdt = findViewById(R.id.dataRegister);
        this.emailEdt = findViewById(R.id.emailRegister);
        this.user = new User();
        this.userService = new UserService();
        user = (User) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    public void onClick(View view) {
        user.setDateOfBirthday(String.valueOf(dataEdt.getText()));
        user.setEmail(String.valueOf(emailEdt.getText()));
        userService.sendUser(user, this);
        System.out.println(user.getName());
        startActivity(new Intent(this, LoginActivity.class));
    }
}
