package co.descubra;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView desc;
    private ViewFlipper viewFlipper;
    private Button cbutton;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.cbutton = findViewById(R.id.btnCadastrar);
        cbutton.setOnClickListener(this);
        this.loginButton = findViewById(R.id.logIn);
        loginButton.setOnClickListener(this);
        this.desc = findViewById(R.id.txtDesc);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "SegoeUIBold.ttf");
        desc.setTypeface(myCustomFont);
        this.viewFlipper = findViewById(R.id.viewFlipper);

        viewFlipper.setFlipInterval(2500);
        viewFlipper.startFlipping();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCadastrar:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.logIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
