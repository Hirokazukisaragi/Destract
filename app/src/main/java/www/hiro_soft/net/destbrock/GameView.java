package www.hiro_soft.net.destbrock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hirokazu on 15/12/01.
 */

/*import android.support.v7.app.AppCompatActivity;

Copyright (C) 2015  Seno Hirokazu

This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation; either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>

*/


public class GameView extends View{
    private Paint paint = new Paint();
   // private Block block = new Block;
    private int zanblock = 25;
    private int aliblock = 1;
    private int row = 0;
    private int col = 0;
    private float padBegin = 0f;
    public float ballX = 480,ballY = 400;
    private float ballspeedX = 15;
    private float ballspeedY = 15;
    private Thread thread;
    private double score = 0;
    private View view;
    private Handler handler = new Handler();
    private final long MSEC = 30;


    int j = 0;
    boolean[] blockflag = new boolean[28];


    int i = 0;
    public GameView(Context context) {
        super(context);


        for(i = 0;i < 28;i++) {
                blockflag[i] = true;
        }




        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (ballX > padBegin && ballX < padBegin + (getWidth() / 6) && ballY >= (getHeight() - 64) && ballY <= getHeight()) {
                            ballspeedY = -ballspeedY;
                        }

                        ballY += ballspeedY;
                        ballX += ballspeedX;
                        invalidate();
                    }
                });
            }
        }, 0, MSEC);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        padBegin = event.getX();
        invalidate();
        return true;
    }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(Color.BLACK);


            setBackgroundColor(Color.BLUE);
            //paint.setStyle(Paint.Style.STROKE);
            int k = 0, l = 0;
            int tmp = 0;
            int foo = 0, bar = 0, baz = 0;
            int hoge = 0;
            float piyo = 0f;
            float oneHLine = canvas.getHeight() / 25f;
            float oneVLine = canvas.getWidth() / 5f;
            boolean clearcheck = false;
            for (k = 0; k < 25; k++) {
                if(ballY <= 0){
                    ballspeedY = -ballspeedY;
                }
                if(ballX < 0){
                    ballspeedX = -ballspeedX;
                }
                if(ballX > (canvas.getWidth())){
                    ballspeedX = -ballspeedX;
                }
                /*
                if(((ballX < padBegin+10)
                        || (ballX > (padBegin + (canvas.getWidth() / 6) -10)))
                        && (ballY >= (canvas.getHeight() -10))) {
                    ballspeedX = -ballspeedX;
                    ballspeedY = - ballspeedY;
                }
                */
                if(ballY > canvas.getHeight()){
                    paint.setTextSize(48);
                    paint.setColor(Color.RED);
                    canvas.drawText("GAME OVER",canvas.getWidth()/3,canvas.getHeight()/2,paint);
                    canvas.drawText("SCORE"+score,canvas.getWidth()/3,canvas.getHeight()/3,paint);

                }
                if(blockflag[k]) {
                    hoge = k;
                    if ((hoge %= 5) == 0) {
                        piyo++;
                    }

                    if (//ballX > ((oneVLine) * (hoge))
                            ballX < ((oneVLine) * (hoge + 1))
                                    && (ballX > ((oneVLine) * hoge))
                                    && (ballY < ((oneHLine) * (piyo + 1)))
                                    && ballY > ((oneHLine) * (piyo))) {
                        //true){
                        blockflag[k] = false;
                        ballspeedY = -ballspeedY;
                        score++;
                        //クリア判定
                        for(foo = 0;foo < 25;foo++){
                            if(blockflag[foo] == true)break;
                            for(foo = 0;foo < 25;foo++){
                                blockflag[foo] = true;
                            }
                        }
                    }
                    paint.setColor(Color.BLACK);
                    //for(l = 0;l < 26; l++) {
                    bar = k % 5;
                    if ((k % 5) == 0 && k != 0) {
                        baz++;
                        if (baz >= 6) {
                            baz = 0;
                        }
                    }
                    if (blockflag[l]) {
                        canvas.drawRect((oneVLine) * bar,
                                //0,
                                (oneHLine) * baz,
                                //0,
                                (oneVLine) * (bar + 1),
                                //100,
                                (oneHLine) * (baz + 1),
                                //100,
                                paint);

                    }
                    bar++;
                    //}

                    //col = 0;
                    //row = 0;
                }
                paint.setColor(Color.YELLOW);
                canvas.drawRect(padBegin, (canvas.getHeight()) - (canvas.getHeight() / 25), (canvas.getWidth() / 6) + padBegin, canvas.getHeight(), paint);

                canvas.drawCircle(ballX, ballY, 32, paint);

            }
        }
}
