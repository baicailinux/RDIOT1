package com.fyang21117.rdiot1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.RDIOT.R;

public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button psw_login = findViewById(R.id.psw_login);
        psw_login.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                com.fyang21117.rdiot1.MainActivity.actionStart(this);
            }
}
