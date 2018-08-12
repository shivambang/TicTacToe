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

public class Circle extends View {

    private static final int START_ANGLE_POINT = 20;

    private Paint paint;
    private RectF rect;

    private float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 20;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(0xfffbf9f1);

        rect = new RectF(50, 50, 200, 200);

        angle = 0;

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    protected void set(int width, int height){
        rect = new RectF(width / 4, height / 4, width - width / 10, height / 4 + width - width / 10 - width / 4);
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }

}
