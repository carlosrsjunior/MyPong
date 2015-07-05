package com.carlosjunior.mypong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.carlosjunior.mypong.constants.PongConstants;
import com.carlosjunior.mypong.entities.Ball;

/**
 * Created by Carlos on 05/07/2015.
 */
public class GameView extends View {

    private Ball ball;
    private BallPositionHandler ballPositionHandler;
    private static final int BALL_POSITION_CODE = 1;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ball = new Ball();
        ballPositionHandler = new BallPositionHandler();
    }

//    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        ballPosition = new Position(PongConstants.INITIAL_X_POSITION, PongConstants.INITIAL_Y_POSITION);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(Color.BLACK);
        canvas.drawLine(canvas.getClipBounds().left, canvas.getClipBounds().top, canvas.getClipBounds().right, canvas.getClipBounds().top, p1);

        Paint p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.GREEN);
        canvas.drawCircle(ball.getXPosition(), ball.getYPosition(), 50, p2);

        ball.move(canvas.getClipBounds());
    }

    public void updateBallPosition() {
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
                    updateBallPosition();
                    break;
            }
        }
    }

}
