package com.akshat_dobriyal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity<play> extends AppCompatActivity {
    boolean gameActive = false;
    // Player representation
    // 0 - x
    // 1 - o
    int activePlayer = 0;
    //To check for a draw
    int flag = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning:
    // 0 - x
    // 1 - o
    // 2 - Null
    int winPositions[][] = { {0,1,2}, {3,4,5}, {6,7,8},
                             {0,3,6}, {1,4,7}, {2,5,8},
                             {0,4,8}, {2,4,6} };
    public void play(View view){
        gameReset(view);
        return;
    }

    public void playerTap(View view){
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive)
            return;
        if(gameState[tappedImage] == 2 && gameActive) {
            flag += 1;
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                for(int[] winPosition: winPositions)
                {
                    if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] ==gameState[winPosition[2]] &&
                            gameState[winPosition[0]]!=2)
                    //Somebody has won! - Find out who!
                    {
                        String winnerStr;
                        gameActive = false;
                        if(gameState[winPosition[0]] == 0)
                            winnerStr = "X has won!!!!!!";
                        else
                            winnerStr = "O has won!!!!!!";
                        //Update the status bar for winner announcement
                        TextView status = findViewById(R.id.status);
                        status.setText(winnerStr);
                        break;
                    }
                    else if(flag != 9){
                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn - Tap to play");
                    }

                }
                String drawStr = null;
                if(flag == 9) {
                    drawStr = "Game Draw!!!!!!";
                    TextView status = findViewById(R.id.status);
                    status.setText(drawStr);
                }

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                for(int[] winPosition: winPositions)
                {
                    if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] ==gameState[winPosition[2]] &&
                            gameState[winPosition[0]]!=2)
                    //Somebody has won! - Find out who!
                    {
                        String winnerStr;
                        gameActive = false;
                        if (gameState[winPosition[0]] == 0)
                            winnerStr = "X has won!!!!!!";
                        else
                            winnerStr = "O has won!!!!!!";
                        //Update the status bar for winner announcement
                        TextView status = findViewById(R.id.status);
                        status.setText(winnerStr);
                        break;
                    }
                    else{
                        TextView status = findViewById(R.id.status);
                        status.setText("X's Turn - Tap to play");
                    }
                }
                String drawStr = null;
                if(flag == 9) {
                    drawStr = "Game Draw!!!!!!";
                    TextView status = findViewById(R.id.status);
                    status.setText(drawStr);
                }
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++)
            gameState[i] = 2;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}