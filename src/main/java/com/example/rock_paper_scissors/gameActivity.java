package com.example.rock_paper_scissors;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import java.lang.String;

public class gameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView userScore=(TextView) findViewById(R.id.userScoreTxtView);
        userScore.setText(getString(R.string.initialValue));

        TextView pcScore=(TextView) findViewById(R.id.pcScoreTxtView);
        pcScore.setText(getString(R.string.initialValue));

    }

    /**
     * A method that randomly creates an int [1-3] which is the machine's choice.
     * @return 1 for rock, 2 for paper, 3 for scissors.
     */
    int computerChoice(){
        Random randomChoice = new Random();
        int pcChoice = randomChoice.nextInt(3)+1;

        ImageView pcChoiceImg = (ImageView) findViewById(R.id.userChoiceImg);
        switch (pcChoice){
            case 1: pcChoiceImg.setImageResource(R.drawable.rock);
               break;
            case 2: pcChoiceImg.setImageResource(R.drawable.paper);
                break;
            case 3: pcChoiceImg.setImageResource(R.drawable.scissors);
                break;
        }

        return pcChoice;
    }

    /**
     * A method that is called when the rockBtn is clicked. Shows user's choice in the center of the screen, get's the machine's choice
     * by calling computerChoice() and finds the winner by calling findWinner().
     * @param view
     */
    public void rockBtnClick(View view) {
        ImageView userChoiceImg = (ImageView) findViewById(R.id.pcChoiceImg);
        userChoiceImg.setImageResource(R.drawable.rock);
        int pcChoice = computerChoice();
        findWinner(1,pcChoice);
    }

    /**
     * A method that is called when the paperBtn is clicked. Shows user's choice in the center of the screen, get's the machine's choice
     * by calling computerChoice() and finds the winner by calling findWinner().
     * @param view
     */
    public void paperBtnClick(View view) {
        ImageView userChoiceImg = (ImageView) findViewById(R.id.pcChoiceImg);
        userChoiceImg.setImageResource(R.drawable.paper);
        int pcChoice = computerChoice();
        findWinner(2,pcChoice);
    }

    /**
     * A method that is called when the scissorsBtn is clicked. Shows user's choice in the center of the screen, get's the machine's choice
     * by calling computerChoice() and finds the winner by calling findWinner().
     * @param view
     */
    public void scissorsBtnClick(View view) {
        ImageView userChoiceImg = (ImageView) findViewById(R.id.pcChoiceImg);
        userChoiceImg.setImageResource(R.drawable.scissors);
        int pcChoice = computerChoice();

        findWinner(3,pcChoice);
    }

    /**
     * A method that finds the winner according to the game's rules. Calls updateScore() to update the winner's score and shows a message
     * about the result and the current score by calling showResultMessage().
     * @param userChoice 1 for rock, 2 for paper, 3 for scissors.
     * @param machineChoice 1 for rock, 2 for paper, 3 for scissors.
     */
    public void findWinner(int userChoice, int machineChoice){
        int result=0;

        if (userChoice==machineChoice){
            //Draw
        }
        else{
            if (userChoice==1){ //User's choice: Rock
                if (machineChoice==2){ //Machine's choice: Paper
                    result=-1;
                }
                else{ //Machine's choice: Scissors
                    result=1;
                }
            }
            else if (userChoice==2){ //User's choice: Paper
                if (machineChoice==1){ //Machine's choice: Rock
                    result=1;
                }
                else{ //Machine's choice: Scissors
                    result=-1;
                }
            }
            else{ //User's choice: Scissors
                if (machineChoice==1){ //Machine's choice: Rock
                    result=-1;
                }
                else{ //Machine's choice: Paper
                    result=1;
                }
            }
        }

        updateScore(result);

        showResultMessage(result);

    }

    /**
     * Shows a message about the result and the current score.
     * @param result 1 if user is the winner, -1 if machine is the winner, 0 for draw.
     */
    public void showResultMessage(int result){

        String msg;

        TextView userScore=(TextView) findViewById(R.id.userScoreTxtView);
        TextView pcScore=(TextView) findViewById(R.id.pcScoreTxtView);

        String userScoreString=userScore.getText().toString();
        String pcScoreString=pcScore.getText().toString();

        if (result==1){
            msg = getString(R.string.winMsgString);
        }
        else if (result==-1){
            msg = getString(R.string.loseMsgString);
        }
        else{
            msg = getString(R.string.drawMsgString);
        }

        String msg1=msg+getString(R.string.scoreMsgString)
               +userScoreString+ " - "+pcScoreString;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialogTitle));

        builder.setMessage(msg1)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialogBtn), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.getWindow().setGravity(Gravity.TOP); //Message will be shown on the top of the screen
        alert.show();

    }

    /**
     * A method that updates the current score according to the result.
     * @param result 1 if user is the winner, -1 if machine is the winner, 0 for draw.
     */
    public void updateScore(int result){
        TextView userScore=(TextView) findViewById(R.id.userScoreTxtView);
        TextView pcScore=(TextView) findViewById(R.id.pcScoreTxtView);

        String userScoreString=userScore.getText().toString();
        String pcScoreString=pcScore.getText().toString();

        int userScoreNumber=Integer.parseInt(userScoreString);
        int pcScoreNumber=Integer.parseInt(pcScoreString);

        if (result == 0){
            //Draw, score remains the same.
        }
        else if(result == 1){
            userScoreNumber++;
            userScore.setText(String.valueOf(userScoreNumber));
        }
        else{ //result=-1
            pcScoreNumber++;
            pcScore.setText(String.valueOf(pcScoreNumber));
        }
    }

    /**
     * Creates an AlertDialog when the user presses the back button on his device. User chooses if he wants to return to the main menu or not.
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.closeTitle))
                .setMessage(getString(R.string.closemsg))
                .setPositiveButton(getString(R.string.closePositive), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(getString(R.string.closeNegative), null)
                .show();
    }

}

