package com.fyang21117.rdiot1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity{

    private ImageView welcomeImg=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        welcomeImg = findViewById(R.id.logo_start);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(3000);
        welcomeImg.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new AnimationImpl());
    }

    public class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.logo);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
           // MainActivity.actionStart(StartActivity.this);
           Intent intent = new Intent(StartActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
