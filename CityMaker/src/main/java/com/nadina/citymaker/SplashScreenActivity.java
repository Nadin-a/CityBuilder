package com.nadina.citymaker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    static MediaPlayer mediaPlayer;
    // Время в милесекундах, в течение которого будет отображаться Splash Screen
    private final int SPLASH_DISPLAY_LENGTH = 4500;
    TextView main_label;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockScreenOrientation();
        setContentView(R.layout.start_game);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        main_label = (TextView) findViewById(R.id.main_label);
        Animation anim = new AlphaAnimation(0.3f, 1.0f);
        anim.setDuration(1200);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        main_label.startAnimation(anim);
        mediaPlayer = MediaPlayer.create(this, R.raw.main);
        mediaPlayer.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                Intent mainIntent = new Intent(SplashScreenActivity.this, CityActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                mediaPlayer.stop();
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    /**
     * Блокируется вращение
     */
    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
