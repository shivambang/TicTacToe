package com.example.shivam.tictactoe;

/**
 * Created by Shivam on 27-02-2017.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class LineAnimation_R extends Animation {

    private Line line;
    int width, height;

    public LineAnimation_R(Line line, int width, int height) {
        this.line = line;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float one = (width - width / 10 - width / 4)*interpolatedTime;

        line.setpXY(width / 4, height / 4, width - width / 10, height / 4 + width - width / 10 - width / 4);
        line.setXY(width - width / 10, height / 4, width - width / 10 - one, height / 4 + one);
        line.requestLayout();
    }

}
