package com.carlosjunior.mypong;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.carlosjunior.mypong.constants.PongConstants;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String player = getIntent().getStringExtra("player");
        if (player == null) {
            player = "";
        }

        EditText txtPlayer = (EditText) findViewById(R.id.edt_player);
        txtPlayer.setText(player);

        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void startGame(View view) {
        int level = 0;
        switch (view.getId()) {
            case R.id.btn_start_easy:
                level = PongConstants.LEVEL_EASY;
                break;
            case R.id.btn_start_hard:
                level = PongConstants.LEVEL_HARD;
                break;
        }
        String player = ((EditText) findViewById(R.id.edt_player)).getText().toString();
        Intent t = new Intent(this, GameActivity.class);
        t.putExtra("player", player);
        t.putExtra("level", level+"");
        startActivity(t);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this, "com.carlosjunior.mypong");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this, "com.carlosjunior.mypong");
    }
}
