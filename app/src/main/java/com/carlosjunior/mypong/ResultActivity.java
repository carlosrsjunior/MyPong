package com.carlosjunior.mypong;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {

    private String player;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        player = getIntent().getStringExtra("player");
        TextView txtPlayer = (TextView) findViewById(R.id.txt_player_name);
        txtPlayer.setText(player);

        score = getIntent().getStringExtra("score");
        TextView txtFinalScore = (TextView) findViewById(R.id.txt_final_score);
        txtFinalScore.setText(getString(R.string.msg_final_score, score));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

    public void replayGame(View view) {
        Intent t = new Intent(this, MainActivity.class);
        t.putExtra("player", player);
        startActivity(t);
    }

    public void postOnFacebook(View view) {
        Intent t = new Intent(this, PublishResultActivity.class);
        t.putExtra("player", player);
        t.putExtra("score", score);
        startActivity(t);
    }

}
