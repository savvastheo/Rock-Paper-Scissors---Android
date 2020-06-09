/*

 */

package com.example.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * A method that starts the gameActivity. Called when newGameBtn is clicked.
     * @param view
     */
    public void newGameBtnClick(View view) {
        startActivity(new Intent(MainActivity.this, gameActivity.class));
    }

    /**
     * A method that kills the process and exits the app. Called when exitGameBtn is clicked.
     * @param view
     */
    public void exitGameBtnClick(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}

