package com.fyang21117.rdiot1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.RDIOT.R;

public class test2Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent intent = new Intent(context,test2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    public TextView test2Text;
    public String cp2="第二个产品";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        test2Text= findViewById(R.id.test2view);
        test2Text.setText(cp2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: {
                finish();
            }break;
            default :break;
        }
        return super.onOptionsItemSelected(item);
    }
}
