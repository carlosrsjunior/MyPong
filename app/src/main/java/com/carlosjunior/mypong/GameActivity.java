package com.carlosjunior.mypong;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carlosjunior.mypong.constants.PongConstants;
import com.carlosjunior.mypong.entities.BallStatus;


public class GameActivity extends ActionBarActivity {

    private TextView txtPlayer;
    private String player;
    private TextView txtVlLifes;
    private int lifes;
    private TextView txtVlScore;
    private int score;
    private GameView gameView;
    private GameViewHandler gameViewHandler;

    private static final int BALL_POSITION_CODE = 1;
    private static final int RESUME_GAME_CODE = 2;
    private static final int FINISH_GAME_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = getIntent().getStringExtra("player");
        txtPlayer = (TextView) findViewById(R.id.txt_player_name);
        txtPlayer.setText(player);

        lifes = PongConstants.INITIAL_LIFES;
        txtVlLifes = (TextView) findViewById(R.id.txt_vl_lifes);
        txtVlLifes.setText(lifes + "");

        score = PongConstants.INITIAL_SCORE;
        txtVlScore = (TextView) findViewById(R.id.txt_vl_score);
        txtVlScore.setText(score + "");

        gameViewHandler = new GameViewHandler();
        gameView = (GameView) findViewById(R.id.gameview);
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
        BallStatus ballStatus = gameView.checkBallMovement();

        if (ballStatus.equals(BallStatus.STOPPED)) {
            if (lifes > 0) {
                Toast.makeText(this, R.string.msg_tryagain, Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.what = RESUME_GAME_CODE;
                gameViewHandler.sendMessageDelayed(msg, PongConstants.RESUME_GAME_DELAY);
            } else {
                Toast.makeText(this, R.string.msg_gameover, Toast.LENGTH_LONG).show();
                Message msg = new Message();
                msg.what = FINISH_GAME_CODE;
                gameViewHandler.sendMessageDelayed(msg, PongConstants.RESUME_GAME_DELAY);
            }
        } else {
            if (ballStatus.equals(BallStatus.REBATED)) {
                updateScore();
            }
            Message msg = new Message();
            msg.what = BALL_POSITION_CODE;
            gameViewHandler.sendMessageDelayed(msg, PongConstants.BALL_MOV_DELAY);
        }
    }

    private void resumeGame() {
        gameView.resume();
    }

    private void updateLifes() {
        txtVlLifes.setText((--lifes) + "");
    }

    private void updateScore() {
        txtVlScore.setText((++score) + "");
    }

    private void showGameResult() {
        Intent t = new Intent(this, PublishResultActivity.class);
        t.putExtra("player", player);
        t.putExtra("score", score+"");
        startActivity(t);
    }
    class GameViewHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case BALL_POSITION_CODE:
                    updateScreen();
                    break;
                case RESUME_GAME_CODE:
                    resumeGame();
                    updateLifes();
                    updateScreen();
                    break;
                case FINISH_GAME_CODE:
                    showGameResult();
                    break;
            }
        }
    }

}
