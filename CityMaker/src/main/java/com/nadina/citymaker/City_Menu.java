package com.nadina.citymaker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Меню города
 *
 * @author Nadina
 *         Created on 19.09.2016.
 */
public class City_Menu extends Activity {

    /**
     * Индекс города
     */
    int index;
    /**
     * Константы для отправки даных
     */
    public final static String MY_INDEX = "my_city_current_index";
    public final static String CITY = "open_my_city";
    public final static int NEED_SHOP = 1;


    SwitchCompat musicSwitch;
    SwitchCompat soundsSwitch;


    /**
     * Эта активность
     */
    public static City_Menu activity;

    public static City_Menu getActivity() {
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.city_settings);

        activity = this;
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        lockScreenOrientation();
        index = getIntent().getExtras().getInt(MY_INDEX);

        musicSwitch = (SwitchCompat) findViewById(R.id.music_switch_compat);
        soundsSwitch = (SwitchCompat) findViewById(R.id.sounds_switch_compat);
        if (CityActivity.wasSwitchMusic) {
            musicSwitch.setChecked(true);
        } else {
            musicSwitch.setChecked(false);
        }
        if (CityActivity.wasSwitchSound) {
            soundsSwitch.setChecked(true);
        } else {
            soundsSwitch.setChecked(false);
        }

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    create_music();
                    CityActivity.mediaPlayer.start();
                    CityActivity.wasSwitchMusic = true;
                } else {
                    CityActivity.mediaPlayer.stop();
                    CityActivity.mediaPlayer.reset();
                    CityActivity.wasSwitchMusic = false;
                }

            }
        });
        soundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    CityActivity.wasSwitchSound = true;

                } else {
                    CityActivity.wasSwitchSound = false;
                }

            }
        });


    }

    public void create_music() {
        CityActivity.mediaPlayer = MediaPlayer.create(this, R.raw.city_music);
        CityActivity.mediaPlayer.setLooping(true);
    }

    /**
     * Что будет открыто
     *
     * @param v - виджет
     */
    public void btnClick(View v) {

        switch (v.getId()) {
            case R.id.open_shop: {
                Intent intent = new Intent(City_Menu.this, CityActivity.class);
                intent.putExtra(MY_INDEX, index);//отправляем индекс города, которому открываем магаз. сделать проверку пришёл ли интент
                intent.putExtra(CITY, NEED_SHOP);
                startActivity(intent);
                break;
            }
            case R.id.back_to_menu: {
                // CityActivity.mediaPlayer.stop();
                CityActivity.setIs_city_view(false);
                Intent intent = new Intent(City_Menu.this, CityActivity.class);

                startActivity(intent);


                break;
            }
        }
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

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        finish();

    }
}
