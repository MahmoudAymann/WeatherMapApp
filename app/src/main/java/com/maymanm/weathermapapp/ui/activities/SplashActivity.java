package com.maymanm.weathermapapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.os.Bundle;

import com.maymanm.weathermapapp.R;
import com.maymanm.weathermapapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = DataBindingUtil.setContentView(this, R.layout.activity_splash);

//        MyApp.getMainComponent().inject(this);

//        startAnim();


    }

    private void startAnim() {
        data.lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
