package com.carlosjunior.mypong;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.carlosjunior.mypong.constants.PongConstants;


public class GameActivity extends ActionBarActivity {

    private GameView gameView;
    private TextView txtVlLifes;
    private int lifes;
    private TextView txtVlScore;
    private int score;
    private GameViewHandler gameViewHandler;
    private static final int BALL_POSITION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameViewHandler = new GameViewHandler();
        gameView = (GameView) findViewById(R.id.gameview);

        lifes = 0;
        txtVlLifes = (TextView) findViewById(R.id.txt_vl_lifes);
        txtVlLifes.setText(lifes + "");

        score = 0;
        txtVlScore = (TextView) findViewById(R.id.txt_vl_score);
        txtVlScore.setText(score + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStart() {
        super.onStart();
        updateScreen();
    }

    @Override
    public void finish() {
        gameViewHandler.removeCallbacksAndMessages(null);
        super.finish();
    }

    public void moveToLeft(View view) {
        gameView.moveRacketToLeft();
    }

    public void moveToRight(View view) {
        gameView.moveRacketToRight();
    }

    private void updateScreen() {
        boolean rebated = gameView.checkBallMovement();
        updateScore(rebated);
        Message msg = new Message();
        msg.what = BALL_POSITION_CODE;
        gameViewHandler.sendMessageDelayed(msg, PongConstants.BALL_MOV_DELAY);
    }

    private void updateScore(boolean rebated) {
        if (rebated) {
            txtVlScore.setText((++score) + "");
        }
    }

    class GameViewHandler extends Handler {

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
