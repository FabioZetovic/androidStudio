package com.example.farcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TikTak extends AppCompatActivity implements View.OnClickListener{
    private TextView player1Rez, player2Rez, playerStatus;
    private Button [] buttons = new Button[9];
    private Button resetiraj;
    MediaPlayer tiktakPL;
    private int player1RezCount, player2RezCount, rountCount;
    boolean activePlayer;
    ImageView natrag;
    int[]gameState = {2,2,2,2,2,2,2,2,2};

    int[][] pobjednickePozicije = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tik_tak);

        player1Rez = (TextView) findViewById(R.id.playerJedanScore);
        player2Rez = (TextView)findViewById(R.id.playerDvaScore);
        playerStatus = (TextView)findViewById(R.id.playerStatus);

        resetiraj = (Button)findViewById(R.id.reset);

        natrag = (ImageView) findViewById(R.id.strelica);

        tiktakPL = MediaPlayer.create(TikTak.this,R.raw.dust);
        tiktakPL.setLooping(true);
        tiktakPL.start();

            for(int i = 0; i < buttons.length;i++){
                String buttonID = "btn"+i;
                int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i] = (Button) findViewById(resourceID);
                buttons[i].setOnClickListener(this);
            }

            rountCount = 0;
            player2RezCount = 0;
            player2RezCount = 0;
            activePlayer = true;



        View.OnClickListener nazad = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idiNazad();
            }
        };natrag.setOnClickListener(nazad);
    }


public void idiNazad(){
    Intent intentP = new Intent(this,MainActivity.class);
    startActivity(intentP);
    tiktakPL.stop();

}

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if (activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;

        }else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        if(checkWinner()){
            if(activePlayer){
                player1RezCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player 1 je pobjedio",Toast.LENGTH_LONG).show();
                playAgain();
            }else {
                player2RezCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player 2 je pobjedio",Toast.LENGTH_LONG).show();
                playAgain();
            }
        }else if(rountCount ==9){
            playAgain();
            Toast.makeText(this,"Nema pobjednika",Toast.LENGTH_LONG).show();
        }else {
            activePlayer = !activePlayer;
        }
        if(player1RezCount>player2RezCount){
            playerStatus.setText("Player 1 vodi");
        }else if (player2RezCount>player1RezCount){
            playerStatus.setText("Player 2 vodi");
        }else {
            playerStatus.setText("");
        }

        resetiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                player1RezCount = 0;
                player2RezCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int[] winningPosition : pobjednickePozicije){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]
                            && gameState[winningPosition[0]] != 2){
                    winnerResult = true;

            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        player1Rez.setText(Integer.toString(player1RezCount));
        player2Rez.setText(Integer.toString(player2RezCount));
    }


    public void playAgain(){
        rountCount = 0;
        activePlayer = true;

        for(int i = 0;i < buttons.length;i++){
            gameState[i] = 2;
            buttons[i].setText("");

        }
    }


}