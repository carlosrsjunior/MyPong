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
import com.carlosjunior.mypong.entities.BallStatus;
import com.carlosjunior.mypong.entities.Racket;

/**
 * Created by Carlos on 05/07/2015.
 */
public class GameView extends View {

    private Racket racket;
    private Ball ball;

    private Paint paintBall;
    private Paint paintRacket;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        racket = new Racket();
//      ball = new Ball();
        //initializeMoveables(getClipBounds());

        paintBall = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBall.setColor(Color.GREEN);
        paintRacket = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRacket.setColor(Color.RED);

    }

     @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (racket == null || ball == null) {
            initializeMoveables(canvas.getClipBounds());
        }

        canvas.drawCircle(ball.getXPosition(), ball.getYPosition(), ball.getRadius(), paintBall);
        canvas.drawRect(new Rect(racket.getLeft(), racket.getTop(), racket.getRight(), racket.getBottom()), paintRacket);

        ball.move(canvas.getClipBounds());
        racket.move(canvas.getClipBounds());
    }

    private void initializeMoveables(Rect bounds) {
        int racketXPosition = (int) (bounds.right * (PongConstants.RACKET_INITIAL_X_POSITION_PERCENT / (double) 100));
        int racketYPosition = (int) (bounds.bottom * (PongConstants.RACKET_INITIAL_Y_POSITION_PERCENT / (double) 100));
        racket = new Racket(racketXPosition, racketYPosition);

        ball = new Ball();
    }

    public BallStatus checkBallMovement() {
        invalidate();
        if (ball != null) {
            return ball.checkMovement(racket);
        } else {
            return BallStatus.STARTING;
        }
    }

    public void moveRacketToLeft() {
        racket.setDirectionXToLeft();
    }

    public void moveRacketToRight() {
        racket.setDirectionXToRight();
    }

    public void resume() {
        racket.reset();
        ball.reset();
    }
}
