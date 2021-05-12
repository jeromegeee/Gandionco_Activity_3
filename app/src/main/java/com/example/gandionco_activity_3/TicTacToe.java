package com.example.gandionco_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {
    TextView text_player_one_score, text_player_two_score, text_round_count;
    Button button_reset;
    Button[] button = new Button[9];
    int playeronescore, playertwoscore, roundcount;
    boolean activeplayer;

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningstates = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6} //d
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        text_player_one_score = (TextView) findViewById(R.id.txt_player_one_score);
        text_player_two_score = (TextView) findViewById(R.id.txt_player_two_score);
        text_round_count = (TextView) findViewById(R.id.txt_round_count);
        button_reset = (Button) findViewById(R.id.btn_reset);

        for (int i = 0; i < button.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button[i] = (Button) findViewById(resourceID);
            button[i].setOnClickListener(this);

        }

        roundcount = 0;
        playeronescore = 0;
        playertwoscore = 0;
        activeplayer = true;
    }

    @Override
    public void onClick(View v) {
        Log.i("test", "button is pressed");

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gamestatepointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));

        if (activeplayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFFFFFFF"));
            gamestate[gamestatepointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FF9800"));
            gamestate[gamestatepointer] = 1;
        }
        roundcount++;

        if (checkwinner()) {
            if (activeplayer) {
                Toast.makeText(this, "Player One Wins!", Toast.LENGTH_SHORT).show();
                playeronescore++;
                updateplayerscore();
                playagain();
            } else {
                Toast.makeText(this, "Player Two Wins!", Toast.LENGTH_SHORT).show();
                playertwoscore++;
                updateplayerscore();
                playagain();
            }
        } else if (roundcount == 9) {
            playagain();
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        } else {
            activeplayer = !activeplayer;
        }

        button_reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playagain();
            playeronescore = 0;
            playertwoscore = 0;
            updateplayerscore();
        }
    });

}
    public boolean checkwinner() {
        boolean winnerresult = false;

        for (int[] winningstate : winningstates) {
            if (gamestate[winningstate[0]] == gamestate[winningstate[1]] &&
                    gamestate[winningstate[1]] == gamestate[winningstate[2]] &&
                    gamestate[winningstate[0]] != 2) {
                winnerresult = (true);
            }
        }
        return winnerresult;
    }

    public void updateplayerscore(){
    text_player_one_score.setText(Integer.toString(playeronescore));
    text_player_two_score.setText(Integer.toString(playertwoscore));
    }

    public void playagain() {
        roundcount = 0;
        activeplayer = true;

        for (int i = 0; i < button.length; i++) {
            gamestate[i] = 2;
            button[i].setText("");
        }
    }
    }
