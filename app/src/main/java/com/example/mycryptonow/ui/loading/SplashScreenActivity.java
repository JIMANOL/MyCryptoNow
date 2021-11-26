package com.example.mycryptonow.ui.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycryptonow.R;
import com.example.mycryptonow.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {
    //Variables globales
    private LottieAnimationView billetera;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        billetera = findViewById(R.id.ivLogoSplash);
        activity= this;

        //FirebaseAuth.getInstance().signOut();

        billetera.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        billetera.playAnimation();

    }
}