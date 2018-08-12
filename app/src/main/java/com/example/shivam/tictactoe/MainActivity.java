package com.example.shivam.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    int arr[][] = new int[8][3];
    int toe[][] = new int[3][3];
    int num = 1;
    int mode = 0;
    int level;
    int count_x, count_o;
    int player;
    TextView sx, so, res;
    CheckBox cb1, cb2, cb3;
    LinearLayout b1, b2, b3, b4, b5, b6, b7, b8, b9;
    LinearLayout l_level, middle; View space;
    CardView x_score, o_score;
    View x_view, o_view, x1, o1, x2, o2, x3, o3, x4, o4, x5, o5, x6, o6, x7, o7, x8, o8, x9, o9;
    View v1, v2, v3, v4, v5, v6, v7, v8;
    Circle circle;
    String Q = "X";
    int width, height;
    int color_light, color_dark, stroke;
    boolean bool_o, bool_x, bool_xo, bool_oo, bool_win, audio;
    MediaPlayer x, o, win, lose, tie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        x1 = findViewById(R.id.x1);
        o1 = findViewById(R.id.c1);
        x2 = findViewById(R.id.x2);
        o2 = findViewById(R.id.c2);
        x3 = findViewById(R.id.x3);
        o3 = findViewById(R.id.c3);
        x4 = findViewById(R.id.x4);
        o4 = findViewById(R.id.c4);
        x5 = findViewById(R.id.x5);
        o5 = findViewById(R.id.c5);
        x6 = findViewById(R.id.x6);
        o6 = findViewById(R.id.c6);
        x7 = findViewById(R.id.x7);
        o7 = findViewById(R.id.c7);
        x8 = findViewById(R.id.x8);
        o8 = findViewById(R.id.c8);
        x9 = findViewById(R.id.x9);
        o9 = findViewById(R.id.c9);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);
        v7 = findViewById(R.id.v7);
        v8 = findViewById(R.id.v8);
        space = findViewById(R.id.view);
        sx = (TextView)findViewById(R.id.score_x);
        so = (TextView)findViewById(R.id.score_o);

        l_level = (LinearLayout)findViewById(R.id.l_level);
        middle = (LinearLayout) findViewById(R.id.mainLayout);
        o_score = (CardView)findViewById(R.id.o_score);
        x_score = (CardView)findViewById(R.id.x_score);
        x_view = (View)findViewById(R.id.xview);
        o_view = (View)findViewById(R.id.oview);
        b1 = (LinearLayout) findViewById(R.id.button);
        b2 = (LinearLayout) findViewById(R.id.button2);
        b3 = (LinearLayout) findViewById(R.id.button3);
        b4 = (LinearLayout) findViewById(R.id.button4);
        b5 = (LinearLayout) findViewById(R.id.button5);
        b6 = (LinearLayout) findViewById(R.id.button6);
        b7 = (LinearLayout) findViewById(R.id.button7);
        b8 = (LinearLayout) findViewById(R.id.button8);
        b9 = (LinearLayout) findViewById(R.id.button9);
        res = (TextView) findViewById(R.id.rbutton);
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);

        x = MediaPlayer.create(MainActivity.this, R.raw.cross2);
        o = MediaPlayer.create(MainActivity.this, R.raw.circle2);
        win = MediaPlayer.create(MainActivity.this, R.raw.win2);
        lose = MediaPlayer.create(MainActivity.this, R.raw.lose2);
        tie = MediaPlayer.create(MainActivity.this, R.raw.tie2);
        level = 0;
        count_x = 0; count_o = 0;
        bool_win = false;

        elevate(15, 6);
        x_view.setBackgroundColor(0xff1ba2cb);
        setLevel(level);
        setCheck(true, false, false);
        Bundle extras = getIntent().getExtras();
        mode = extras.getInt("Mode");
        color(extras.getInt("Color"));
        audio = extras.getBoolean("Audio");
        play();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels / 4;
        height = metrics.heightPixels / 6;
        for(int x = 0; x < 8; x++)
            Arrays.fill(arr[x], -1);
        for(int x = 0; x < 3; x++)
            Arrays.fill(toe[x], -1);

        cb1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                easy(view);
            }
        });

        cb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                med(view);
            }
        });

        cb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                hard(view);
            }
        });
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



    public void color(int color){

        stroke = 0xff424242;
        switch(color){

            case 0:
                color_light = 0xff1eb4e2;
                color_dark =  0xff1890b4;
                break;
            case 1:
                color_light = 0xfff6809a;
                color_dark = 0xffe64f7e;
                break;
            case 2:
                color_light = 0xff61e064;
                color_dark = 0xff4db350;
                break;
            case 3:
                color_light = 0xfff6ec35;
                color_dark = 0xffc4bc2a;
                break;
            case 4:
                color_light = 0xffff7519;
                color_dark =  0xffcc5d14;
                stroke = 0xff343434;
                break;
            case 5:
                color_light = 0xffaab8c2;
                color_dark = 0xff88939b;
                break;
            case 6:
                color_light = 0xff8c88cd;
                color_dark = 0xff706ca4;
                stroke = 0xff343434;
                break;
            case 7:
                color_light = 0xffffdf8b;
                color_dark = 0xffccb26f;
                break;
            case 8:
                color_light = 0xff2dde98;
                color_dark = 0xff24b179;
                break;
            case 9:
                color_light = 0xffe74c3c;
                color_dark = 0xffb83c30;
                stroke = 0xff343434;
                break;
            default:
                color_light = 0xff1eb4e2;
                color_dark =  0xff1890b4;
                break;
        }

        middle.setBackgroundColor(color_light);
        v1.setBackgroundColor(color_dark);
        v2.setBackgroundColor(color_dark);
        v3.setBackgroundColor(color_dark);
        v4.setBackgroundColor(color_dark);
        v5.setBackgroundColor(color_dark);
        v6.setBackgroundColor(color_dark);
        v7.setBackgroundColor(color_dark);
        v8.setBackgroundColor(color_dark);
        res.setTextColor(color_dark);

    }

    public void elevate(int x, int o){
        x_score.setCardElevation(x);
        o_score.setCardElevation(o);
        int white = 0xfff5f8fa;
        if(x == 6 && o == 6) {
            x_view.setBackgroundColor(white);
            o_view.setBackgroundColor(white);
        }
        else if(x == 6){
            x_view.setBackgroundColor(white);
            o_view.setBackgroundColor(color_light);
        }
        else{
            x_view.setBackgroundColor(color_light);
            o_view.setBackgroundColor(white);
        }
    }

    public void setCheck(boolean a, boolean b, boolean c){
        cb1.setChecked(a);
        cb2.setChecked(b);
        cb3.setChecked(c);
    }

    public void easy(View view){
        reset(view);
        level = 0;
        setLevel(level);
        setCheck(true, false, false);

    }

    public void med(View view){
        reset(view);

        level = 1;
        setLevel(level);
        setCheck(false, true, false);
    }

    public void hard(View view){
        reset(view);

        level = 2;
        setLevel(level);
        setCheck(false, false, true);
    }

    public void insane(View view){
        reset(view);

        level = 3;
        setLevel(level);
    }
    public void setLevel(int lvl){
        int x;
        switch(lvl){
            case 0:
                x = (int)(Math.random()*10);
                if(x < 8)
                    bool_o = false;
                else
                    bool_o = true;

                x = (int)(Math.random()*10);
                if(x < 8)
                    bool_x = false;
                else
                    bool_x = true;

                bool_xo = false;
                bool_oo = false;
                break;

            case 1:
                x = (int)(Math.random()*10);
                if(x < 2)
                    bool_o = false;
                else
                    bool_o = true;

                bool_x = true;

                x = (int)(Math.random()*10);
                if(x < 2)
                    bool_xo = true;
                else
                    bool_xo = false;

                x = (int)(Math.random()*10);
                if(x < 2)
                    bool_oo = true;
                else
                    bool_oo = false;
                break;

            case 2:

                bool_o = true;
                bool_x = true;
                x = (int)(Math.random()*10);
                if(x < 3)
                    bool_xo = false;
                else
                    bool_xo = true;

                bool_oo = true;
                break;

            case 3:
                bool_o = true;
                bool_x = true;
                bool_oo = true;
                x = (int)(Math.random()*10);
                if(x > 0)
                    bool_xo = true;
                else
                    bool_xo = false;
                break;

        }
    }

    public void setPlay(int player){
        if(player == 1) {
            Q = "X";
            num = player;
            elevate(15, 6);
        }
        else {
            Q = "O";
            num = player;
            elevate(6, 15);
            comp_screen(comp_move(toe, arr, 1), Q.charAt(0));
        }
    }

    public void play_x(View view){
        player = 1;
        reset(view);

    }
    public void play_o(View view){
        player = 0;
        reset(view);

    }

    public void animateLine(Line line) {
        line.set(stroke);
        final Line liner = line;
        Animation animation = new LineAnimation(liner, width, height);
        final Animation animation_r = new LineAnimation_R(liner, width, height);
        animation.setDuration(100);
        animation_r.setDuration(100);
        if(audio)
        x.start();
        liner.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                liner.startAnimation(animation_r);
            }
        }, 100);


    }
    public void animateCircle(Circle circle){
        circle.set(width, height);
        Animation animation = new CircleAnimation(circle);
        animation.setDuration(200);
        if(audio)
        o.start();
        circle.startAnimation(animation);
    }
    public void set1(View view){

        if(man(0, 0)) {

            if(Q == "X"){
                x1.setVisibility(View.VISIBLE);
                o1.setVisibility(View.GONE);
                Line line = (Line)findViewById(R.id.x1);
                animateLine(line);

            }

            else{
                o1.setVisibility(View.VISIBLE);
                x1.setVisibility(View.GONE);
                circle = (Circle)findViewById(R.id.c1);
                animateCircle(circle);
            }

            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();

        }
    }
    public void set2(View view){
        if(man(0, 1)) {

            if(Q == "X")
                {
                    x2.setVisibility(View.VISIBLE);
                    o2.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x2);
                    animateLine(line);
                }
            else{
                o2.setVisibility(View.VISIBLE);
                x2.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c2);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set3(View view){
        if(man(0, 2)) {

            if(Q == "X")
                {
                    x3.setVisibility(View.VISIBLE);
                    o3.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x3);
                    animateLine(line);                }
            else{
                o3.setVisibility(View.VISIBLE);
                x3.setVisibility(View.GONE);
                circle = (Circle)findViewById(R.id.c3);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }

    public void set4(View view){
        if(man(1, 0)) {

            if(Q == "X")
                {
                    x4.setVisibility(View.VISIBLE);
                    o4.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x4);
                    animateLine(line);                }
            else{
                o4.setVisibility(View.VISIBLE);
                x4.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c4);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set5(View view){
        if(man(1, 1)) {

            if(Q == "X")
                {
                    x5.setVisibility(View.VISIBLE);
                    o5.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x5);
                    animateLine(line);
                }
            else{
                o5.setVisibility(View.VISIBLE);
                x5.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c5);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set6(View view){
        if(man(1, 2)) {

            if(Q == "X")
                {
                    x6.setVisibility(View.VISIBLE);
                    o6.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x6);
                    animateLine(line);                }
            else{
                o6.setVisibility(View.VISIBLE);
                x6.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c6);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set7(View view){
        if(man(2, 0)) {

            if(Q == "X")
                {
                    x7.setVisibility(View.VISIBLE);
                    o7.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x7);
                    animateLine(line);                }
            else{
                o7.setVisibility(View.VISIBLE);
                x7.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c7);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set8(View view){
        if(man(2, 1)) {

            if(Q == "X")
                {
                    x8.setVisibility(View.VISIBLE);
                    o8.setVisibility(View.GONE);
                    Line line = (Line)findViewById(R.id.x8);
                    animateLine(line);                }
            else{
                o8.setVisibility(View.VISIBLE);
                x8.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c8);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();
        }
    }
    public void set9(View view){
        if(man(2, 2)) {

            if(Q == "X"){
                x9.setVisibility(View.VISIBLE);
                o9.setVisibility(View.GONE);
                Line line = (Line)findViewById(R.id.x9);
                animateLine(line);            }

            else{
                o9.setVisibility(View.VISIBLE);
                x9.setVisibility(View.GONE);
               circle = (Circle)findViewById(R.id.c9);
                animateCircle(circle);
            }
            if(bool_win){
                restart();  return;
            }
            if(mode == 0)
                comp();

        }
    }

    public void comp(){
        final int b;
        b = comp_move(toe, arr, num % 2);
        num++;
        if(!check(arr))
            restart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            comp_screen(b, Q.charAt(0));

            }
        }, 250);


    }
    public void comp_screen(int b, char c){
        switch(c) {
            case 'X':

                switch (b) {
                    case 1:
                        o1.setVisibility(View.VISIBLE);
                        x1.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c1);
                        animateCircle(circle);
                        break;
                    case 2:
                        o2.setVisibility(View.VISIBLE);
                        x2.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c2);
                        animateCircle(circle);
                        break;
                    case 3:
                        o3.setVisibility(View.VISIBLE);
                        x3.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c3);
                        animateCircle(circle);
                        break;
                    case 4:
                        o4.setVisibility(View.VISIBLE);
                        x4.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c4);
                        animateCircle(circle);
                        break;
                    case 5:
                        o5.setVisibility(View.VISIBLE);
                        x5.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c5);
                        animateCircle(circle);
                        break;
                    case 6:
                        o6.setVisibility(View.VISIBLE);
                        x6.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c6);
                        animateCircle(circle);
                        break;
                    case 7:
                        o7.setVisibility(View.VISIBLE);
                        x7.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c7);
                        animateCircle(circle);
                        break;
                    case 8:
                        o8.setVisibility(View.VISIBLE);
                        x8.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c8);
                        animateCircle(circle);
                        break;
                    case 9:
                        o9.setVisibility(View.VISIBLE);
                        x9.setVisibility(View.GONE);
                        circle = (Circle) findViewById(R.id.c9);
                        animateCircle(circle);
                        break;
                    default:
                        break;
                }
                break;

            case 'O':
                Line line;
                switch (b) {
                    case 1:
                        x1.setVisibility(View.VISIBLE);
                        o1.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x1);
                        animateLine(line);
                        break;
                    case 2:
                        x2.setVisibility(View.VISIBLE);
                        o2.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x2);
                        animateLine(line);
                        break;
                    case 3:
                        x3.setVisibility(View.VISIBLE);
                        o3.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x3);
                        animateLine(line);
                        break;
                    case 4:
                        x4.setVisibility(View.VISIBLE);
                        o4.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x4);
                        animateLine(line);
                        break;
                    case 5:
                        x5.setVisibility(View.VISIBLE);
                        o5.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x5);
                        animateLine(line);
                        break;
                    case 6:
                        x6.setVisibility(View.VISIBLE);
                        o6.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x6);
                        animateLine(line);
                        break;
                    case 7:
                        x7.setVisibility(View.VISIBLE);
                        o7.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x7);
                        animateLine(line);
                        break;
                    case 8:
                        x8.setVisibility(View.VISIBLE);
                        o8.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x8);
                        animateLine(line);
                        break;
                    case 9:
                        x9.setVisibility(View.VISIBLE);
                        o9.setVisibility(View.GONE);
                        line = (Line)findViewById(R.id.x9);
                        animateLine(line);
                        break;
                    default:
                        break;
                }
                break;
        }
    }
    public void play(){
        if(mode == 1){
            x_score.setClickable(false);
            o_score.setClickable(false);
            l_level.setVisibility(LinearLayout.GONE);
            space.setVisibility(View.VISIBLE);
        }
        else{
            x_score.setClickable(true);
            o_score.setClickable(true);
            l_level.setVisibility(LinearLayout.VISIBLE);
            space.setVisibility(View.GONE);
        }
        player = 1;
        num = player;
        setPlay(player);
    }
    public void restart(){
        enable(false);
    }
    public void reset(View view) {
        enable(true);
        for(int x = 0; x < 8; x++)
            Arrays.fill(arr[x], -1);
        for(int x = 0; x < 3; x++)
            Arrays.fill(toe[x], -1);

        bool_win = false;
        count_x = 0; count_o = 0;
        sx.setText("-");
        so.setText("-");
        setLevel(level);
        if(player == 1)
            setPlay(1);
        else
            setPlay(0);

    }


    public void reset_game(View view) {
        enable(true);
        for(int x = 0; x < 8; x++)
            Arrays.fill(arr[x], -1);
        for(int x = 0; x < 3; x++)
            Arrays.fill(toe[x], -1);
        bool_win = false;
        num = player;
        setLevel(level);
        setPlay(player);
    }

    public void enable(boolean bool) {

        if(bool) {
            x1.setVisibility(View.GONE);
            x2.setVisibility(View.GONE);
            x3.setVisibility(View.GONE);
            x4.setVisibility(View.GONE);
            x5.setVisibility(View.GONE);
            x6.setVisibility(View.GONE);
            x7.setVisibility(View.GONE);
            x8.setVisibility(View.GONE);
            x9.setVisibility(View.GONE);
            o1.setVisibility(View.GONE);
            o2.setVisibility(View.GONE);
            o3.setVisibility(View.GONE);
            o4.setVisibility(View.GONE);
            o5.setVisibility(View.GONE);
            o6.setVisibility(View.GONE);
            o7.setVisibility(View.GONE);
            o8.setVisibility(View.GONE);
            o9.setVisibility(View.GONE);
            b1.setClickable(true);
            b2.setClickable(true);
            b3.setClickable(true);
            b4.setClickable(true);
            b5.setClickable(true);
            b6.setClickable(true);
            b7.setClickable(true);
            b8.setClickable(true);
            b9.setClickable(true);
            b1.setBackgroundColor(color_light);
            b2.setBackgroundColor(color_light);
            b3.setBackgroundColor(color_light);
            b4.setBackgroundColor(color_light);
            b5.setBackgroundColor(color_light);
            b6.setBackgroundColor(color_light);
            b7.setBackgroundColor(color_light);
            b8.setBackgroundColor(color_light);
            b9.setBackgroundColor(color_light);
        }
        else{
            b1.setClickable(false);
            b2.setClickable(false);
            b3.setClickable(false);
            b4.setClickable(false);
            b5.setClickable(false);
            b6.setClickable(false);
            b7.setClickable(false);
            b8.setClickable(false);
            b9.setClickable(false);
        }
    }

    public void win(int xo, int pos){
        switch(xo) {

            case 0:
                switch (pos) {
                    case 0:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o2.setVisibility(View.GONE);
                                o3.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o2.setVisibility(View.VISIBLE);
                                o3.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o2.setVisibility(View.GONE);
                                o3.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o2.setVisibility(View.VISIBLE);
                                o3.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 1:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o4.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o6.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o4.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o6.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o4.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o6.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o4.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o6.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 2:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o7.setVisibility(View.GONE);
                                o8.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o7.setVisibility(View.VISIBLE);
                                o8.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o7.setVisibility(View.GONE);
                                o8.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o7.setVisibility(View.VISIBLE);
                                o8.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 3:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o4.setVisibility(View.GONE);
                                o7.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o4.setVisibility(View.VISIBLE);
                                o7.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o4.setVisibility(View.GONE);
                                o7.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o4.setVisibility(View.VISIBLE);
                                o7.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 4:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o2.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o8.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o2.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o8.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o2.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o8.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o2.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o8.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 5:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.GONE);
                                o6.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.VISIBLE);
                                o6.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.GONE);
                                o6.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.VISIBLE);
                                o6.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 6:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o1.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 7:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o7.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o7.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.GONE);
                                o5.setVisibility(View.GONE);
                                o7.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                o3.setVisibility(View.VISIBLE);
                                o5.setVisibility(View.VISIBLE);
                                o7.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    default:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_light);
                                v2.setBackgroundColor(color_light);
                                v3.setBackgroundColor(color_light);
                                v4.setBackgroundColor(color_light);
                                v5.setBackgroundColor(color_light);
                                v6.setBackgroundColor(color_light);
                                v7.setBackgroundColor(color_light);
                                v8.setBackgroundColor(color_light);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_dark);
                                v2.setBackgroundColor(color_dark);
                                v3.setBackgroundColor(color_dark);
                                v4.setBackgroundColor(color_dark);
                                v5.setBackgroundColor(color_dark);
                                v6.setBackgroundColor(color_dark);
                                v7.setBackgroundColor(color_dark);
                                v8.setBackgroundColor(color_dark);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_light);
                                v2.setBackgroundColor(color_light);
                                v3.setBackgroundColor(color_light);
                                v4.setBackgroundColor(color_light);
                                v5.setBackgroundColor(color_light);
                                v6.setBackgroundColor(color_light);
                                v7.setBackgroundColor(color_light);
                                v8.setBackgroundColor(color_light);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_dark);
                                v2.setBackgroundColor(color_dark);
                                v3.setBackgroundColor(color_dark);
                                v4.setBackgroundColor(color_dark);
                                v5.setBackgroundColor(color_dark);
                                v6.setBackgroundColor(color_dark);
                                v7.setBackgroundColor(color_dark);
                                v8.setBackgroundColor(color_dark);
                            }
                        }, 1000);
                        break;
                }
                break;
            case 1:
                switch(pos){
                    case 0:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x2.setVisibility(View.GONE);
                                x3.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x2.setVisibility(View.VISIBLE);
                                x3.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x2.setVisibility(View.GONE);
                                x3.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x2.setVisibility(View.VISIBLE);
                                x3.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 1:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x4.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x6.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x4.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x6.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x4.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x6.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x4.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x6.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 2:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x7.setVisibility(View.GONE);
                                x8.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x7.setVisibility(View.VISIBLE);
                                x8.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x7.setVisibility(View.GONE);
                                x8.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x7.setVisibility(View.VISIBLE);
                                x8.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 3:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x4.setVisibility(View.GONE);
                                x7.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x4.setVisibility(View.VISIBLE);
                                x7.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x4.setVisibility(View.GONE);
                                x7.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x4.setVisibility(View.VISIBLE);
                                x7.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 4:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x2.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x8.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x2.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x8.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x2.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x8.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x2.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x8.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 5:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.GONE);
                                x6.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.VISIBLE);
                                x6.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.GONE);
                                x6.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.VISIBLE);
                                x6.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 6:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x9.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x1.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x9.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    case 7:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x7.setVisibility(View.GONE);
                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x7.setVisibility(View.VISIBLE);
                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.GONE);
                                x5.setVisibility(View.GONE);
                                x7.setVisibility(View.GONE);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                x3.setVisibility(View.VISIBLE);
                                x5.setVisibility(View.VISIBLE);
                                x7.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                        break;

                    default:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_light);
                                v2.setBackgroundColor(color_light);
                                v3.setBackgroundColor(color_light);
                                v4.setBackgroundColor(color_light);
                                v5.setBackgroundColor(color_light);
                                v6.setBackgroundColor(color_light);
                                v7.setBackgroundColor(color_light);
                                v8.setBackgroundColor(color_light);

                            }
                        }, 400);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_dark);
                                v2.setBackgroundColor(color_dark);
                                v3.setBackgroundColor(color_dark);
                                v4.setBackgroundColor(color_dark);
                                v5.setBackgroundColor(color_dark);
                                v6.setBackgroundColor(color_dark);
                                v7.setBackgroundColor(color_dark);
                                v8.setBackgroundColor(color_dark);

                            }
                        }, 600);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_light);
                                v2.setBackgroundColor(color_light);
                                v3.setBackgroundColor(color_light);
                                v4.setBackgroundColor(color_light);
                                v5.setBackgroundColor(color_light);
                                v6.setBackgroundColor(color_light);
                                v7.setBackgroundColor(color_light);
                                v8.setBackgroundColor(color_light);
                            }
                        }, 800);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v1.setBackgroundColor(color_dark);
                                v2.setBackgroundColor(color_dark);
                                v3.setBackgroundColor(color_dark);
                                v4.setBackgroundColor(color_dark);
                                v5.setBackgroundColor(color_dark);
                                v6.setBackgroundColor(color_dark);
                                v7.setBackgroundColor(color_dark);
                                v8.setBackgroundColor(color_dark);
                            }
                        }, 1000);
                        break;
                }
                break;
        }
    }
    public boolean man(int p, int q){

        if(toe[p][q] != -1){

            return false;
        }

            toe[p][q] = num % 2;

            arr[p][q] = num % 2;
            arr[q + 3][p] = num % 2;
            if (p == q)
                arr[6][p] = num % 2;
            if (2 - p == q)
                arr[7][p] = num % 2;


            if (mode == 0) {

                // To check if player has won
                if(!check(arr)){
                    bool_win = true;
                    return true;
                }

                if(Q == "X")
                    elevate(6, 15);
                else
                    elevate(15, 6);
                num++;

                if(Q == "X")
                    elevate(15, 6);
                else
                    elevate(6, 15);

            } else {
                num++;

                if (num % 2 == 0) {
                    Q = "X";

                    elevate(6, 15);
                } else {
                    Q = "O";

                    elevate(15, 6);
                }

                if(!check(arr)){
                    bool_win = true;
                    return true;
                }

            }

        bool_win = false;
        return true;
    }
    public int comp_move(int t[][], int a[][], int num){
        int bool = 0, pos = -1;
        int s[][] = new int[9][2];

        if(bool_o) {
            for (int x = 0; x < 8; x++) {
                bool = 0;

                for (int y = 0; y < 3; y++) {
                    if (a[x][y] == num)
                        bool++;

                    else
                        pos = y;

                }
                if (bool == 2) {
                    int m = 0;
                    if (x < 3) {
                        m = pos;
                        pos = x;
                    } else if (x > 2 && x < 6)
                        m = x - 3;
                    else if (x > 6)
                        m = 2 - pos;
                    else
                        m = pos;
                    if (t[pos][m] == -1) {
                        return fill(pos, m, num);
                    }
                }
            }
        }

        if(bool_x) {
            for (int x = 0; x < 8; x++) {
                bool = 0;
                for (int y = 0; y < 3; y++) {
                    if (a[x][y] == (num + 1) % 2)
                        bool++;

                    else
                        pos = y;

                }
                if (bool == 2) {
                    int m = 0;
                    if (x < 3) {
                        m = pos;
                        pos = x;
                    } else if (x > 2 && x < 6)
                        m = x - 3;
                    else if (x > 6)
                        m = 2 - pos;
                    else
                        m = pos;
                    if (t[pos][m] == -1) {
                        return fill(pos, m, num);
                    }
                }
            }
        }

        int ind = -1;
        if(bool_xo) {
            if (t[1][1] == -1)
                return fill(1, 1, num);
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (t[x][y] == -1) {
                        fill(x, y, num);

                        if (check_move(num)) {
                            ind++;
                            s[ind][0] = x;
                            s[ind][1] = y;
                        }

                        fill(x, y, -1);
                    }
                }
            }

            if (ind != -1) {
                int x = (int) (Math.random() * ind);
                return fill(s[x][0], s[x][1], num);
            }
        }

        ind = -1;
        if(bool_oo) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (t[x][y] == -1) {


                        if (check_move_o(x, y)) {
                            ind++;
                            s[ind][0] = x;
                            s[ind][1] = y;
                        }


                    }
                }
            }

            if (ind != -1) {
                int x = (int) (Math.random() * ind);
                return fill(s[x][0], s[x][1], num);
            }
        }
        int x = 0, y = 0;

        ind = -1;
        for(x = 0; x < 3; x++) {
            for (y = 0; y < 3; y++) {
                if (t[x][y] == -1) {
                    ind++;
                    s[ind][0] = x;
                    s[ind][1] = y;
                }
            }
        }
        if(ind == -1)
            return -1;
        else{
            x = (int)(Math.random()*ind);
            return fill(s[x][0], s[x][1], num);
        }


    }



    public boolean check_move(int n){
        int j = 0, k =0, m = 0, p = 0, q = 0, r = 0;
        for(int x = 0; x < 8; x++){
            j = 0; k = 0;
            int y = 0;
            for(y = 0; y < 3; y++){
                if(arr[x][y] == n)
                    j++;
                else if(arr[x][y] == -1){
                    k++; m = y;}

            }
            if(j == 2 && k == 1) {
                r++;
                p = m; q = x;
            }
        }
        switch(r){
            case 2:
                return true;
            case 1:
                if(n == 0) {
                    m = 0;
                    if (q < 3) {
                        m = p;
                        p = q;
                    } else if (q > 2 && q < 6)
                        m = q - 3;
                    else if (q > 6)
                        m = 2 - p;
                    else
                        m = p;
                    if (toe[p][m] == -1) {
                        fill(p, m, (num + 1) % 2);
                        if(check_move((num + 1) % 2)){
                            fill(p, m, -1);
                            return false;
                        }
                        else {
                            fill(p, m, -1);
                            return true;
                        }
                    }
                }
                else return false;
        }
        return false;
    }

    public boolean check_move_o(int p, int q) {
        int j = 0, k = 0, r = 0;
        for(j = 0; j < 3; j++){
            if(arr[p][j] != -1)
                break;
        }
        if(j == 3)
            r++;

        for(j = 0; j < 3; j++){
            if(arr[q + 3][j] != -1)
                break;
        }
        if(j == 3)
            r++;
        if(p == q){
            for(j = 0; j < 3; j++){
                if(arr[6][j] != -1)
                    break;
            }
            if(j == 3)
                r++;
        }

        if(2 - p == q){
            for(j = 0; j < 3; j++){
                if(arr[7][j] != -1)
                    break;
            }
            if(j == 3)
                r++;
        }
        if(r > 1)
            return true;
        else
            return false;
    }
    public int fill(int p, int q, int r){
        toe[p][q] = r;
        int b = p*3 + 1 + q;
        arr[p][q] = r;
        arr[q + 3][p] = r;
        if(p == q)
            arr[6][p] = r;
        if(2 - p == q)
            arr[7][p] = r;
        return b;
    }
    public boolean check(int a[][]){
        boolean bool = false;
        int pos = 0;
        for(int temp[] : a){
            bool = true;

            for(int tmp : temp){
                if(tmp != player){

                    bool = false;
                    break;
                }
            }

            if(bool){
                win(player, pos);
                if(player == 1) {
                    count_x++;
                    sx.setText(Integer.toString(count_x));
                }
                else {
                    count_o++;
                    so.setText(Integer.toString(count_o));
                }

                elevate(6, 6);
                if(audio)
                win.start();
                return false;
            }
            pos++;
        }
        pos = 0;
        for(int temp[] : a){
            bool = true;
            for(int tmp : temp){
                if(tmp != ((player + 1) % 2)){
                    bool = false;
                    break;
                }
            }

            if(bool){
                win((player + 1) % 2, pos);
                if(player == 0) {
                    count_x++;
                    sx.setText(Integer.toString(count_x));
                }
                else {
                    count_o++;
                    so.setText(Integer.toString(count_o));
                }
                elevate(6, 6);
                if(audio)
                lose.start();
                return false;
            }
            pos++;
        }
        bool = true;
        loop:
        for(int temp[] : a){

            for(int tmp : temp){
                if(tmp == -1){
                    bool = false;
                    break loop;
                }
            }

        }
        if(bool){
            win(player, 9);
            elevate(6, 6);
            if(audio)
            tie.start();
            return false;
        }

        return true;
    }
    static void sop(int toe[][]){
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                if(toe[x][y] == 1)
                    System.out.print(" X ");
                else if(toe[x][y] == 0)
                    System.out.print(" O ");
                else
                    System.out.print(" - ");

            }
            System.out.println("\n");
        }
    }
}
