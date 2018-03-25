package com.csit.ramesh.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView Yourscore,HighScore;
    Button tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Yourscore= (TextView) findViewById(R.id.yourScore);
        HighScore= (TextView) findViewById(R.id.highScore);
        tryAgain= (Button) findViewById(R.id.tryAgain);
        int score= getIntent().getIntExtra("SCORE",0);
        Yourscore.setText("Your Score :"+score);
        SharedPreferences sharedPreferences= getSharedPreferences("GAME_DATA",MODE_PRIVATE);

        int highScore= sharedPreferences.getInt("High_Score",0);
        if(score>highScore)
        {
            HighScore.setText("High Score : "+score);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("High_Score",score);
            editor.commit();
        }
        else
        {
            HighScore.setText("High Score : "+highScore);
        }

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(result.this,MainActivity.class));
                finish();
            }
        });
    }
}
