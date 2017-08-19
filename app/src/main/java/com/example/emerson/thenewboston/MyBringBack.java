package com.example.emerson.thenewboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {

    Bitmap gBall;
    float changingY;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(
            getResources(), R.drawable.greenball
        );
        font = Typeface.createFromAsset(
            context.getAssets(), "G-Unit.ttf"
        );
        changingY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 254, 10, 50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(100);
        textPaint.setTypeface(font);

        canvas.drawColor(Color.WHITE);
        canvas.drawText(
            "mybringback", canvas.getWidth()/2, 200, textPaint
        );
        canvas.drawBitmap(
            gBall, (canvas.getWidth()/10), changingY, null
        );

        if (changingY < canvas.getHeight()) {
            changingY += 10;
        } else {
            changingY = 0;
        }

        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 500);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }

}
