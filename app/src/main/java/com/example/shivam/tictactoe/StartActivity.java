package com.example.shivam.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.prefs.Preferences;

public class StartActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    int color;
    boolean audio;
    LinearLayout linearLayout, settingLayout, infoLayout;
    ImageView ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, vol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        settings = getSharedPreferences(PREFS_NAME, 0);
        color = settings.getInt("Color", 0);
        audio = settings.getBoolean("Audio", true);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        settingLayout = (LinearLayout) findViewById(R.id.settingLayout);
        infoLayout = (LinearLayout) findViewById(R.id.infoLayout);
        ch1 = (ImageView) findViewById(R.id.ch1);
        ch2 = (ImageView) findViewById(R.id.ch2);
        ch3 = (ImageView) findViewById(R.id.ch3);
        ch4 = (ImageView) findViewById(R.id.ch4);
        ch5 = (ImageView) findViewById(R.id.ch5);
        ch6 = (ImageView) findViewById(R.id.ch6);
        ch7 = (ImageView) findViewById(R.id.ch7);
        ch8 = (ImageView) findViewById(R.id.ch8);
        ch9 = (ImageView) findViewById(R.id.ch9);
        ch10 = (ImageView) findViewById(R.id.ch10);
        vol = (ImageView) findViewById(R.id.vol);

        setCheck(color);
        if(audio) {
            vol.setImageResource(R.drawable.ic_audio_on);
        }
        else{
            vol.setImageResource(R.drawable.ic_audio_off);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setCheck(int color){
        ch1.setVisibility(ImageView.INVISIBLE);
        ch2.setVisibility(ImageView.INVISIBLE);
        ch3.setVisibility(ImageView.INVISIBLE);
        ch4.setVisibility(ImageView.INVISIBLE);
        ch5.setVisibility(ImageView.INVISIBLE);
        ch6.setVisibility(ImageView.INVISIBLE);
        ch7.setVisibility(ImageView.INVISIBLE);
        ch8.setVisibility(ImageView.INVISIBLE);
        ch9.setVisibility(ImageView.INVISIBLE);
        ch10.setVisibility(ImageView.INVISIBLE);

        switch(color){
            case 0:
                ch1.setVisibility(ImageView.VISIBLE);
                break;
            case 1:
                ch2.setVisibility(ImageView.VISIBLE);
                break;
            case 2:
                ch3.setVisibility(ImageView.VISIBLE);
                break;
            case 3:
                ch4.setVisibility(ImageView.VISIBLE);
                break;
            case 4:
                ch9.setVisibility(ImageView.VISIBLE);
                break;
            case 5:
                ch6.setVisibility(ImageView.VISIBLE);
                break;
            case 6:
                ch5.setVisibility(ImageView.VISIBLE);
                break;
            case 7:
                ch8.setVisibility(ImageView.VISIBLE);
                break;
            case 8:
                ch7.setVisibility(ImageView.VISIBLE);
                break;
            case 9:
                ch10.setVisibility(ImageView.VISIBLE);
                break;

        }
    }
    public void single_play(View view){
        Intent intent = new Intent(this, MainActivity.class);
        color = settings.getInt("Color", 0);
        intent.putExtra("Mode", 0);
        intent.putExtra("Color", color);
        intent.putExtra("Audio", audio);
        startActivity(intent);
    }

    public void multi_play(View view){
        Intent intent = new Intent(this, MainActivity.class);
        color = settings.getInt("Color", 0);
        intent.putExtra("Mode", 1);
        intent.putExtra("Color", color);
        intent.putExtra("Audio", audio);
        startActivity(intent);
    }

    public void audio(View view){
        editor = settings.edit();
        if(audio) {
            audio = false;
            editor.putBoolean("Audio", false);
            vol.setImageResource(R.drawable.ic_audio_off);
        }
        else {
            audio = true;
            editor.putBoolean("Audio", true);
            vol.setImageResource(R.drawable.ic_audio_on);
        }
        editor.commit();
    }
    public void popup(View view){
        linearLayout.setVisibility(LinearLayout.GONE);
        settingLayout.setVisibility(LinearLayout.VISIBLE);
    }

    public void info_popup(View view){
        linearLayout.setVisibility(LinearLayout.GONE);
        infoLayout.setVisibility(LinearLayout.VISIBLE);
    }
    public void close_popup(View view){
        linearLayout.setVisibility(LinearLayout.VISIBLE);
        settingLayout.setVisibility(LinearLayout.GONE);
        infoLayout.setVisibility(LinearLayout.GONE);
    }

    public void blue(View view){
        editor = settings.edit();
        editor.putInt("Color", 0);
        editor.commit();
        setCheck(0);
    }

    public void pink(View view){
        editor = settings.edit();
        editor.putInt("Color", 1);
        editor.commit();
        setCheck(1);
    }

    public void green(View view){
        editor = settings.edit();
        editor.putInt("Color", 2);
        editor.commit();
        setCheck(2);
    }
    public void yellow(View view){
        editor = settings.edit();
        editor.putInt("Color", 3);
        editor.commit();
        setCheck(3);
    }
    public void orange(View view){
        editor = settings.edit();
        editor.putInt("Color", 4);
        editor.commit();
        setCheck(4);
    }
    public void grey(View view){
        editor = settings.edit();
        editor.putInt("Color", 5);
        editor.commit();
        setCheck(5);
    }
    public void purple(View view){
        editor = settings.edit();
        editor.putInt("Color", 6);
        editor.commit();
        setCheck(6);
    }
    public void skin(View view){
        editor = settings.edit();
        editor.putInt("Color", 7);
        editor.commit();
        setCheck(7);
    }
    public void torqs(View view){
        editor = settings.edit();
        editor.putInt("Color", 8);
        editor.commit();
        setCheck(8);
    }
    public void red(View view){
        editor = settings.edit();
        editor.putInt("Color", 9);
        editor.commit();
        setCheck(9);
    }

    public void Random(View view){
        editor = settings.edit();
        if(settings.getBoolean("Random", false))
            editor.putBoolean("Random", false);
        else
            editor.putBoolean("Random", true);
        editor.commit();
    }
}

