package com.example.shivam.tictactoe;

/**
 * Created by Shivam on 27-02-2017.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAnimation extends Animation {

    private Circle circle;

    public CircleAnimation(Circle circle) {
        this.circle = circle;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = (360 * interpolatedTime);

        circle.setAngle(angle);
        circle.requestLayout();
    }

}
