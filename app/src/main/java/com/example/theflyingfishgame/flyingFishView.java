package com.example.theflyingfishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class flyingFishView extends View {

    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int canvasWidth, canvasheight;

    private int redX, redY, redSpeed = 19;
    private Paint redPaint = new Paint();

    private int yellowX, yellowY, yellowSpeed = 9;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 25;
    private Paint greenPaint = new Paint();

    private int score;

    private boolean touch = false;

    private Bitmap backgroundImage;

    private Paint scorePaint = new Paint();

    private Bitmap life[] = new Bitmap[2];


    public flyingFishView(Context context)
    {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
       greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasheight = canvas .getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        int minFishY = fish[0] .getHeight();
        int maxFishY = canvasheight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY)
        {
           fishY =minFishY;
        }
        if (fishY > maxFishY)
        {
            fishY =maxFishY;
        }
        fishSpeed = fishSpeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        }
        else
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }


        yellowX = yellowX - yellowSpeed;
        if (hitballchecker(yellowX,yellowY))
        {
            score = score + 10;
            yellowX= -100;
        }
        if (yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        greenX = greenX - greenSpeed;
        if (hitballchecker(greenX,greenY))
        {
            score = score + 20;
           greenX= -100;
        }
        if (greenX < 0)
        {
            greenX = canvasWidth + 21;
           greenY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        redX = redX - redSpeed;
        if (hitballchecker(redX,redY))
        {
            life[0] = life[1];
            redX= -100;
        }
        if (redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 20, yellowPaint);
        canvas.drawCircle(greenX, greenY, 25, greenPaint);
        canvas.drawCircle(redX, redY,30,redPaint);
        canvas.drawText("Score : " + score, 20, 60, scorePaint);
        canvas.drawBitmap(life[0] , 380, 10, null);
        canvas.drawBitmap(life[0] , 480, 10, null);
        canvas.drawBitmap(life[0] , 580, 10, null);
        }
    public boolean hitballchecker(int x , int y )
    {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
            return true;
        }
        return false;


    };


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
       if (event.getAction() == MotionEvent.ACTION_DOWN);
        {
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}
