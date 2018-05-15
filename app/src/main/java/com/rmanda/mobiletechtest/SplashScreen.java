package com.rmanda.mobiletechtest;

/**
 * Created by RMB on 2/21/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class SplashScreen extends Activity
{
    private Subscription subscription;
    private ProgressBar progressBar;

    Observer observer = new Observer() {
        @Override
        public void onCompleted() {


        }
        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onNext(Object o) {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StartAnimations();
    }

    @Override
    public void onBackPressed() {
        subscription.unsubscribe();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        subscription.unsubscribe();
        super.onPause();
    }

    @Override
    protected void onResume() {
        subscription = Observable.timer(3, TimeUnit.SECONDS).subscribe(observer);
        super.onResume();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l= findViewById(R.id.splashsc);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }

}
