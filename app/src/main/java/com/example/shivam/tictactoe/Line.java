package com.example.shivam.tictactoe;

/**
 * Created by Shivam on 27-02-2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Line extends View {

    private static final int START_ANGLE_POINT = 90;

    private Paint paint;
    private float x1, x2, y1, y2, px1, px2, py1, py2;



    public Line(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 20;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        x1 = 0; y1 = 0;
        x2 = 0; y2 = 0;
        px1 = 0; py1 = 0;
        px2 = 0; py2 = 0;
        paint.setColor(0xff545454);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(px1, py1, px2, py2, paint);
        canvas.drawLine(x1, y1, x2, y2, paint);


    }

    protected void set(int color){
        paint.setColor(color);
    }
    public void setXY(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setpXY(float x1, float y1, float x2, float y2) {
        this.px1 = x1;
        this.py1 = y1;
        this.px2 = x2;
        this.py2 = y2;
    }
}
