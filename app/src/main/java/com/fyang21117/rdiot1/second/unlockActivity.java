package com.fyang21117.rdiot1.second;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.fyang21117.rdiot1.MainActivity;
import com.fyang21117.rdiot1.R;

public class unlockActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, unlockActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        setTitle("输入应用锁密码");

        EditText editText = findViewById(R.id.unlock_num);
        //为EditText设置监听，注意监听类型为TextWatcher
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("1028") ) {
                    MainActivity.actionStart(unlockActivity.this);
                }
                else
                    Toast.makeText(unlockActivity.this,"Unlocking failed!\nPlease input again!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                    //MainActivity.actionStart(unlockActivity.this);
            }
        });
    }
}
