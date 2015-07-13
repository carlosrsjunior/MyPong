package com.carlosjunior.mypong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.carlosjunior.mypong.constants.PongConstants;
import com.carlosjunior.mypong.entities.Ball;
import com.carlosjunior.mypong.entities.Racket;

/**
 * Created by Carlos on 05/07/2015.
 */
public class GameView extends View {

    private Racket racket;
    private Ball ball;
    private BallPositionHandler ballPositionHandler;
    private static final int BALL_POSITION_CODE = 1;

    private Paint paintBall;
    private Paint paintRacket;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        racket = new Racket();
        ball = new Ball();
        ballPositionHandler = new BallPositionHandler();

        paintBall = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBall.setColor(Color.GREEN);
        paintRacket = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRacket.setColor(Color.RED);

    }

//    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        ballPosition = new Position(PongConstants.INITIAL_X_POSITION, PongConstants.INITIAL_Y_POSITION);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(ball.getXPosition(), ball.getYPosition(), ball.getRadius(), paintBall);
        canvas.drawRect(new Rect(racket.getLeft(), racket.getTop(), racket.getRight(), racket.getBottom()), paintRacket);

        ball.move(canvas.getClipBounds());
        racket.move(canvas.getClipBounds());

    }

    public void updateScreen() {
       // ball.move();
        invalidate();
        Message msg = new Message();
        msg.what = BALL_POSITION_CODE;
        ballPositionHandler.sendMessageDelayed(msg, PongConstants.BALL_MOV_DELAY);
    }

    public void resetHandler() {
        ballPositionHandler.removeCallbacksAndMessages(null);
    }

    class BallPositionHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case BALL_POSITION_CODE:
                    updateScreen();
                    break;
            }
        }
    }

}
